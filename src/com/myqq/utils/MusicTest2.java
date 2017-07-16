package com.myqq.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import sun.audio.AudioPlayer;

public class MusicTest2 {

	private InputStream inputStream = null;
	private String file = "D:/ДњТы/JAVA/MyGFQQ/src/com/myqq/client/sounds/msg.wav";

	public MusicTest2(String filename) {
		this.file = filename;
	}

	public void play() throws IOException {
		inputStream = new FileInputStream(new File(file));
		AudioPlayer.player.start(inputStream);
	}

	

}