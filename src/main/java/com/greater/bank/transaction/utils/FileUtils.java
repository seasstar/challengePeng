package com.greater.bank.transaction.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * reading/writing files on the filesystem.
 *
 * @author Peng Wang
 */
public enum FileUtils {
	INSTANCE;
	private static final Logger log = LoggerFactory.getLogger(FileUtils.class);


	public List<Path> listFiles(String pendingDir) {
		Path path = Paths.get(pendingDir);
		FileUtils.log.info("Loading files from pending path : {}", path);

		try (Stream<Path> paths = Files.walk(path)) {
			return paths.filter(Files::isRegularFile).collect(Collectors.toList());
		} catch (IOException e) {
			throw new RuntimeException("Error reading files from " + path, e);
		}
	}


	public List<String> readLinesFromFile(Path path) {
		FileUtils.log.info("Loading lines from file : {}", path);

		try {
			@SuppressWarnings("resource")
			Stream<String> lines = Files.lines(path);
			return lines.collect(Collectors.toList());
		} catch (IOException e) {
			throw new RuntimeException("Error reading file " + path, e);
		}
	}


	public void writeFile(Path path, String content) {
		FileUtils.log.info("Writing file : {}", path);

		try {
			Files.write(path, content.getBytes());
		} catch (IOException e) {
			throw new RuntimeException("Error writing file " + path, e);
		}
	}


	public void moveFile(Path from, Path to) {
		try {
			Files.move(from, to, StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException e) {
			throw new RuntimeException("Error moving file from " + from + " to " + to, e);
		}
	}


}
