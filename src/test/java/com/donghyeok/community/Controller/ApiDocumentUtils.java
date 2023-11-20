package com.donghyeok.community.Controller;

import org.springframework.restdocs.operation.preprocess.OperationRequestPreprocessor;
import org.springframework.restdocs.operation.preprocess.OperationResponsePreprocessor;
import org.springframework.restdocs.operation.preprocess.Preprocessors;

public interface ApiDocumentUtils {

    static OperationRequestPreprocessor getDocumentRequest() {
        return Preprocessors.preprocessRequest(
                Preprocessors.modifyUris() // (1)
                        .scheme("https")
                        .host("docs.api.com")
                        .removePort(),
                Preprocessors.prettyPrint()); // (2)
    }

    static OperationResponsePreprocessor getDocumentResponse() {
        return Preprocessors.preprocessResponse(Preprocessors.prettyPrint()); // (3)
    }
}
