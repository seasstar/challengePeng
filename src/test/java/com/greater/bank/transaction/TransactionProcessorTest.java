package com.greater.bank.transaction;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.greater.bank.transaction.config.AppConfiguration;
import com.greater.bank.transaction.config.TransactionProperties;
import com.greater.bank.transaction.utils.FileUtils;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { AppConfiguration.class })
public class TransactionProcessorTest {

	TransactionProperties props;
	@Rule
	public TemporaryFolder reportsDir = new TemporaryFolder(Paths.get("/tmp").toFile());
	@Rule
	public TemporaryFolder achivedDir = new TemporaryFolder(Paths.get("/tmp").toFile());
	@Before
	public void before() throws IOException {
		this.props = new TransactionProperties();
		String pendingDir = new ClassPathResource("pendingTest").getFile().toPath().toString();
		this.props.setPendingDir(pendingDir);
		this.props.setReportsDir(this.reportsDir.getRoot().toPath().toString());
		this.props.setArchiveDir(this.achivedDir.getRoot().toPath().toString());
		System.out.println("pendingDir is " + this.props.getPendingDir());
		System.out.println("ArchiveDir is " + this.props.getArchiveDir());
		this.tp.setTransactionProperties(this.props);
	}
	@Autowired
	TransactionProcessor tp;
	@Test
	public void executeCorrectly() {

		List<Path> pendings = FileUtils.INSTANCE.listFiles(this.props.getPendingDir());
		this.tp.execute();
		List<Path> reports = FileUtils.INSTANCE.listFiles(this.props.getReportsDir());
		Assert.assertEquals(pendings.size(), reports.size());
	}



	@After
	public void after() {
		// Move the archived files back to the pending directory
		for (Path path : FileUtils.INSTANCE.listFiles(this.props.getArchiveDir())) {
			FileUtils.INSTANCE.moveFile(path, Paths.get(this.props.getPendingDir()));
		}
	}
}
