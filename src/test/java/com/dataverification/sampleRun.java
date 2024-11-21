package com.dataverification;

import java.io.FileNotFoundException;

import org.baseclass.BaseClass;

import com.beep.SuccessSound;

public class sampleRun extends BaseClass {

	public static void main(String[] args) throws Exception {

		try {
			throw new FileNotFoundException();
		} catch (Exception e) {
			SuccessSound.musicFile();

		}

	}
}
