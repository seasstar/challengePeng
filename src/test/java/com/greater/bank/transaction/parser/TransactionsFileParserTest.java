package com.greater.bank.transaction.parser;

import java.io.IOException;
import java.nio.file.Path;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.core.io.ClassPathResource;

import com.greater.bank.transaction.model.TransactionsFile;

public class TransactionsFileParserTest {

	@Test
	public void skippedUnvalidedLines() throws IOException {
		Path path = new ClassPathResource("pending/finance_customer_transactions-corrupt_lines.csv").getFile().toPath();
		TransactionsFile file = TransactionsFileParser.INSTANCE.fromPath(path);

		// There should be one good line and six corrupt lines
		Assert.assertEquals(1, file.getNumAccountsProcessed().intValue());
		Assert.assertEquals(6, file.getNumSkippedLines().intValue());
	}

	@Test
	public void emptyFileHandledCorrectly() throws IOException {
		Path path = new ClassPathResource("pending/finance_customer_transactions-empty.csv").getFile().toPath();
		TransactionsFile file = TransactionsFileParser.INSTANCE.fromPath(path);

		Assert.assertEquals(0, file.getNumAccountsProcessed().intValue());
		Assert.assertEquals(0, file.getNumSkippedLines().intValue());
	}

	@Test
	public void headerOnlyHandledCorrectly() throws IOException {
		Path path = new ClassPathResource("pending/finance_customer_transactions-header_only.csv").getFile().toPath();
		TransactionsFile file = TransactionsFileParser.INSTANCE.fromPath(path);

		Assert.assertEquals(0, file.getNumAccountsProcessed().intValue());
		Assert.assertEquals(0, file.getNumSkippedLines().intValue());
	}



}
