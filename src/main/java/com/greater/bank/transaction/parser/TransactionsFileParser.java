package com.greater.bank.transaction.parser;

import java.nio.file.Path;
import java.util.List;

import org.apache.commons.lang3.math.NumberUtils;

import com.greater.bank.transaction.model.Transaction;
import com.greater.bank.transaction.model.TransactionsFile;
import com.greater.bank.transaction.utils.FileUtils;

public enum TransactionsFileParser {
	INSTANCE;
	/**
	 * input path to get TransactionsFile
	 *
	 * @param path
	 * @return TransactionsFile
	 */
	public TransactionsFile fromPath(Path path) {

		TransactionsFile tf = new TransactionsFile(path.getFileName().toString());
		List<String> lines = FileUtils.INSTANCE.readLinesFromFile(path);
		if (lines != null && lines.size() > 0) {
			lines.remove(0);// remove first line
		}
		for (String line : lines) {
			tf.addTransaction(this.getTransactionsFromLine(line));
		}
		return tf;
	}

	/**
	 * input single line and return Transaction object
	 *
	 * @param line
	 * @return Transaction
	 */
	private Transaction getTransactionsFromLine(String line) {
		String[] parts = line.replace(" ", "").split(",");

		if (parts.length != 2) { // if line isn't split by comma return null
			return null;
		}
		// validates customer account number
		if (!org.apache.commons.lang3.StringUtils.isNumeric(parts[0])) {
			return null;
		}

		// validates transaction amount
		if (!NumberUtils.isCreatable(parts[1])) {
			return null;
		}
		return new Transaction(Integer.parseInt(parts[0]), Double.parseDouble(parts[1]));
	}

}