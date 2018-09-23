package com.zomu.t.epion.tropic.test.tool.rest.runner;

import com.zomu.t.epion.tropic.test.tool.core.context.EvidenceInfo;
import com.zomu.t.epion.tropic.test.tool.core.command.runner.CommandRunner;
import com.zomu.t.epion.tropic.test.tool.rest.command.ExecuteRestApi;
import com.zomu.t.epion.tropic.test.tool.rest.type.HttpMethodType;
import okhttp3.*;
import org.apache.http.protocol.HTTP;
import org.slf4j.Logger;

import java.util.Map;

public class ExecuteRestApiRunner implements CommandRunner<ExecuteRestApi> {

    @Override
    public void execute(
            final ExecuteRestApi command,
            final Map<String, Object> globalScopeVariables,
            final Map<String, Object> scenarioScopeVariables,
            final Map<String, Object> flowScopeVariables,
            final Map<String, EvidenceInfo> evidences,
            Logger logger) throws Exception {


        // メソッド解決
        HttpMethodType httpMethodType =
                HttpMethodType.valueOfByValue(command.getRequest().getMethod().toLowerCase());

        // クライアント作成
        OkHttpClient client = new OkHttpClient();

        // ヘッダ作成
        Headers headers = Headers.of(command.getRequest().getHeaders());

        // URL作成
        HttpUrl.Builder urlBuilder = HttpUrl.parse(command.getRequest().getTargetUrl()).newBuilder();
        command.getRequest().getQueryParameters().stream().forEach(x -> urlBuilder.addQueryParameter(x.getName(), x.getValue()));
        HttpUrl url = urlBuilder.build();

        // リクエスト作成
        Request.Builder requestBuilder = new Request.Builder().headers(headers).url(url);
        MediaType mimeType = MediaType.parse("application/json; charset=utf-8");
        Request request = null;

        switch (httpMethodType) {
            case GET:
                request = requestBuilder.get().build();
                break;
            case DELETE:
                request = requestBuilder.delete().build();
                break;
            case PUT:
                request = requestBuilder
                        .put(RequestBody.create(mimeType, command.getRequest().getBody())).build();
                break;
            case POST:
                request = requestBuilder
                        .post(RequestBody.create(mimeType, command.getRequest().getBody())).build();
                break;
            case PATCH:
                request = requestBuilder
                        .patch(RequestBody.create(mimeType, command.getRequest().getBody())).build();
                break;
            default:

        }


        Response response = client.newCall(request).execute();


    }
}
