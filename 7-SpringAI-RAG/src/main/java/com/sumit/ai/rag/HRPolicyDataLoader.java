package com.sumit.ai.rag;

import jakarta.annotation.PostConstruct;
import org.apache.tika.Tika;
import org.springframework.ai.document.Document;
import org.springframework.ai.reader.tika.TikaDocumentReader;
import org.springframework.ai.transformer.splitter.TextSplitter;
import org.springframework.ai.transformer.splitter.TokenTextSplitter;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class HRPolicyDataLoader {

    private final VectorStore vectorStore;
    public HRPolicyDataLoader(VectorStore vectorStore) {
        this.vectorStore = vectorStore;
    }

    @Value("classpath:policies/HR_Policies.pdf")
    Resource policyFile;

    @PostConstruct
    public void loadPDFInVectorStore() {
        // STEP-1: Read the pdf content in List<Document>
        TikaDocumentReader tikaDocumentReader = new TikaDocumentReader(policyFile);
        List<Document> docs = tikaDocumentReader.get();

        // STEP-2: Split the documents in small chunks and add in Vector Store
        TextSplitter textSplitter = TokenTextSplitter.builder()
                                        .withChunkSize(200)
                                        .withMaxNumChunks(400)
                                        .build();
        vectorStore.add(textSplitter.split(docs));
    }

}