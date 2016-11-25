package softwareEngineering;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import javax.swing.JPanel;

import javazoom.jl.decoder.Bitstream;
import javazoom.jl.decoder.BitstreamException;
import javazoom.jl.decoder.Decoder;
import javazoom.jl.decoder.DecoderException;
import javazoom.jl.decoder.Header;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.decoder.SampleBuffer;
import javazoom.jl.player.AudioDevice;
import javazoom.jl.player.FactoryRegistry;

class MusicPlayer extends Thread{ // JLayer(�ڹٿ��� mp3 �� �� �ְ��ϴ� ���̺귯��) ���¼ҽ�
	public static final int BUFFER_SIZE = 20000;

	private Decoder decoder;
	private AudioDevice out;
	private ArrayList<Sample> samples;
	private int size;
	private JPanel pane;
	private MadeGround mg;
	private boolean stop, wait;

	public MusicPlayer(JPanel p, MadeGround mg) {
		this.pane = p;
		this.mg = mg;
		this.size = 0;
	}
	
	public boolean isInvalid() {
		return (decoder == null || out == null || samples == null || !out.isOpen());
	}
	
	public int getSize(){
		return size;
	}

	protected boolean getSamples(String path) { // ������ ���� ��η� ���������� �а�
												// SampleŬ������ �������� �� �����͵��� ����
		if (isInvalid())
			return false;
		try {
			Header header;
			SampleBuffer sb;
			FileInputStream in = new FileInputStream(path);
			Bitstream bitstream = new Bitstream(in);
			if ((header = bitstream.readFrame()) == null)
				return false;
			
			int nums = 0;
			ArrayList<Integer> buffer_List = new ArrayList<Integer>();
			
			while (size < BUFFER_SIZE && header != null) {		//���� ����
				sb = (SampleBuffer) decoder.decodeFrame(header, bitstream); 
				samples.add(new Sample(sb.getBuffer(), sb.getBufferLength()));
				
				short[] sum = sb.getBuffer();
				int random = (int)(Math.random() * 2303)+1;
				buffer_List.add(sum[random]/70+500);
				nums += (Math.abs(sum[random])+ Math.abs(sum[random-1]))/70;		//0.78s ���� 1�� �߰�
				size++;
				bitstream.closeFrame();
				header = bitstream.readFrame();
			}
			Ground.setSpeed((float)((translate((float)(nums/size)/(float)150.0))*1.5));
			MadeGround.setDistance(Ground.getSpeed());
			
			int locateX = 1450, locateY = 0;
			mg.addGround(0, 200);		//start ���� ����
			for(int i = 0; i<size - MadeGround.DISTANCE; i++){		//���� ����
				if(i % MadeGround.DISTANCE == 0){
					locateY = buffer_List.get(i);
					mg.addGround(locateX, locateY);
				}
			}
			mg.addGround(-1, -1);		//finish ���� ����
			AudioManagement.TimeThread.setEndTime((int)(Math.round(size * 0.026)) + 1);
			
		} catch (FileNotFoundException e) {
			return false;
		} catch (BitstreamException e) {
			return false;
		} catch (DecoderException e) {
			return false;
		}
		return true;
	}

	private float translate(float f) { // speed�� ��ȯ
										// 0.7 ~ 2.0 -> 2.0 ~ 10.0 ��ȯ
		float temp = f - (float) 0.7;
		temp = temp / (float) 0.013;
		return (float) 1.0 + ((float) 0.04 * temp);
	}

	public boolean open(String path) {
		close();
		try {
			decoder = new Decoder();
			out = FactoryRegistry.systemRegistry().createAudioDevice();
			samples = new ArrayList<Sample>(BUFFER_SIZE);
			size = 0;
			out.open(decoder);
			getSamples(path);
		} catch (JavaLayerException e) {
			decoder = null;
			out = null;
			samples = null;
			return false;
		}

		return true;
	}

	public void stopMusic() {
		stop = true;
	}

	public void close() {
		if ((out != null) && !out.isOpen())
			out.close();
		size = 0;
		samples = null;
		out = null;
		decoder = null;
	}

	static class Sample { // �ش� ������ ������ ��� ���� Ŭ����
		private short[] buffer;
		private int size;

		public Sample(short[] buf, int s) {
			buffer = buf.clone();
			size = s;
		}

		public short[] getBuffer() {
			return buffer;
		}

		public int getSize() {
			return size;
		}
	}
	
	public synchronized void waitMusic(){		//����ȭ�� ���ϸ� illegalMonitorStateException �߻�
		wait = true;
	}

	@Override
	public void run() {
		int index = 0;
		if (isInvalid()) {
			return;
		}
		try{
		for (int i = 0; i < size && !stop; i++) {
			if(wait){
				synchronized(this){
					try {
						this.wait();
					} catch (InterruptedException e) {
						wait = false;
					}
				}
			}
			else{
				out.write(samples.get(i).getBuffer(), 0, samples.get(i).getSize());
				if ((i % MadeGround.DISTANCE) == 0) {
					pane.add(mg.getGround(index++).getLabel());
					pane.repaint();	
				}
				if(i == size-1){
					pane.add(mg.getGround(mg.getGrounds().size()-1).getLabel());
					pane.repaint();
				}
			}
		}
		out.flush();
		}catch (JavaLayerException e) {
			e.printStackTrace();
		}
		if (stop) {
			close();
		}
	}

}