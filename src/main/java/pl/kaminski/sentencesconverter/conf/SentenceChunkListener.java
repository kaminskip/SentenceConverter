package pl.kaminski.sentencesconverter.conf;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.listener.ChunkListenerSupport;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.stereotype.Component;

/**
 * Created by Paweł Kamiński.
 */
@Component
public class SentenceChunkListener extends ChunkListenerSupport {

    public static final int INTERVAL = 1000;

    private static final Logger log = LoggerFactory.getLogger(SentenceChunkListener.class);

    /* (non-Javadoc)
	 * @see org.springframework.batch.core.domain.ChunkListener#afterChunk()
	 */
    @Override
    public void afterChunk(ChunkContext context) {
        int committed = context.getStepContext().getStepExecution().getCommitCount();
        if(committed%INTERVAL == 0){
            log.info("Committed " + committed + " sentences.");
        }
    }
}
