package pl.kaminski.sentencesconverter.integration;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.test.AssertFile;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Integration test
 */
@ContextConfiguration(locations = "/conf/jobs.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class CreateCSVFileTest {

    private static final Log logger = LogFactory.getLog(CreateCSVFileTest.class);

    @Autowired
    private JobLauncherTestUtils jobLauncherTestUtils;

    @Value("${output.reference.filename}")
    private String outputRefFileName;

    @Value("${output.filename}")
    private String outputFileName;

    private Resource resource;

    private Resource refResource;

    @Before
    public void setUp() throws Exception {
        resource = new FileSystemResource(outputFileName);
        refResource = new FileSystemResource(outputRefFileName);
    }

    @Test
    public void testJob() throws Exception {
        JobExecution jobExecution = jobLauncherTestUtils.launchJob();
        Assert.assertEquals("COMPLETED", jobExecution.getExitStatus().getExitCode());
        logger.info("Compare files");
        logger.info(refResource.getFile().getAbsoluteFile());
        logger.info(resource.getFile().getAbsoluteFile());
        AssertFile.assertFileEquals(refResource, resource);
    }
}
