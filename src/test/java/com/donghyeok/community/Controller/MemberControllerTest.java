package com.donghyeok.community.Controller;

import com.donghyeok.community.DTO.MemberDTO;
import com.donghyeok.community.service.MemberService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.GsonJsonParser;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.converter.json.GsonBuilderUtils;
import org.springframework.mock.web.MockMultipartHttpServletRequest;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.restdocs.mockmvc.RestDocumentationResultHandler;
import org.springframework.restdocs.operation.preprocess.Preprocessors;
import org.springframework.restdocs.payload.PayloadDocumentation;
import org.springframework.restdocs.request.RequestDocumentation;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.*;

@AutoConfigureMockMvc
@WebMvcTest(MemberController.class)
@AutoConfigureRestDocs(uriScheme = "https", uriHost = "api.minlog.com", uriPort = 443)
@ExtendWith(RestDocumentationExtension.class)
class MemberControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private  ObjectMapper objectMapper;

    @MockBean
    private MemberService memberService;

    @Test
    public  void TestGet() throws Exception {


        String content = objectMapper.writeValueAsString(
                new MemberDTO.RequestMemberJoin(
                        "vvvv1352","1234","test@naver.com","020"
                ));
//
//        mockMvc.perform(
//                MockMvcRequestBuilders.post("/v1/api/member/signIn")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(content))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andDo(MockMvcResultHandlers.print())
//                .andDo(MockMvcRestDocumentation.document("/v1/api/member/signIn",
//                        Preprocessors.preprocessRequest(Preprocessors.prettyPrint()),
//                        Preprocessors.preprocessResponse(Preprocessors.prettyPrint())))
//                .andExpect(MockMvcResultMatchers.status().isOk());
        this.mockMvc.perform(RestDocumentationRequestBuilders.post("/v1/api/member/signIn")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(content))
                .andExpect(status().isOk())
                .andDo(MockMvcRestDocumentation.document("post-create",
                        ApiDocumentUtils.getDocumentRequest(),
                        ApiDocumentUtils.getDocumentResponse(),
                        PayloadDocumentation.requestFields(
                                PayloadDocumentation.fieldWithPath("member_id").description("memberid"),
                                PayloadDocumentation.fieldWithPath("password").description("password"),
                                PayloadDocumentation.fieldWithPath("email").description("email"),
                                PayloadDocumentation.fieldWithPath("phone_number").description("phonenumber")
                        ),
                        PayloadDocumentation.responseFields(
                                PayloadDocumentation.fieldWithPath("member_id").description("memberid"),
                                PayloadDocumentation.fieldWithPath("password").description("password"),
                                PayloadDocumentation.fieldWithPath("email").description("email"),
                                PayloadDocumentation.fieldWithPath("phone_number").description("phonenumber")
                        )
                        ));
    }
}