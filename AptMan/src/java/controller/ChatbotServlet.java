package controller;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.stream.Collectors;

/**
 *
 * @author WuanTun
 */
public class ChatbotServlet extends HttpServlet {

    // Thay thế bằng API key của bạn
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ChatbotServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ChatbotServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("chatbot.jsp").forward(request, response);
    }

//    @Override
//    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
//        response.setContentType("application/json");
//        PrintWriter out = response.getWriter();
//
//        try {
//            // Đọc và log request body
//            String requestBody = request.getReader().lines().collect(Collectors.joining());
//            System.out.println("Request body: " + requestBody); // Log nội dung yêu cầu
//
//            // Parse JSON
//            JsonObject jsonObject = JsonParser.parseString(requestBody).getAsJsonObject();
//            String userQuery = jsonObject.get("queryResult").getAsJsonObject().get("queryText").getAsString();
//            System.out.println("User Query: " + userQuery); // Log câu hỏi của người dùng
//
//            // Gửi yêu cầu tới OpenAI ChatGPT API và nhận phản hồi
//            String chatGptResponse = getChatGptResponse(userQuery);
//
//            // Trả về JSON response
//            JsonObject responseJson = new JsonObject();
//            responseJson.addProperty("fulfillmentText", chatGptResponse);
//
//            out.print(responseJson.toString());
//            System.out.println("Response sent: " + responseJson.toString());
//
//        } catch (Exception e) {
//            System.err.println("Error processing request: " + e.getMessage()); // Log lỗi
//            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
//            JsonObject errorResponse = new JsonObject();
//            errorResponse.addProperty("error", "Internal Server Error");
//            out.print(errorResponse.toString());
//        } finally {
//            out.flush();
//            out.close();
//        }
//    }
//
//    private static final String OPENAI_API_URL = "https://api.openai.com/v1/chat/completions";
//    private static final String API_KEY = "sk-XOQcoPNuor1AVdWcGt8-ciw4TlU_n0aVOjFKt18DDVT3BlbkFJkg8BcK0IuYG1dz7jKPa3Qsdq3Eqb9e3w-RcNBiVCEA";
//
//    private String getChatGptResponse(String userQuery) throws IOException {
//        // Tạo kết nối tới ChatGPT API
//        URL url = new URL(OPENAI_API_URL);
//        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//        conn.setRequestMethod("POST");
//        conn.setRequestProperty("Authorization", "Bearer " + API_KEY);
//        conn.setRequestProperty("Content-Type", "application/json");
//        conn.setDoOutput(true);
//
//        // Tạo JSON payload cho yêu cầu
//        String jsonPayload = String.format("{\"model\": \"gpt-4\", \"messages\": [{\"role\": \"user\", \"content\": \"%s\"}]}",
//                userQuery);
//
//        System.out.println("Sending request to ChatGPT: " + jsonPayload); // Log nội dung gửi đến ChatGPT
//
//        // Gửi yêu cầu tới ChatGPT
//        try (var outputStream = conn.getOutputStream()) {
//            outputStream.write(jsonPayload.getBytes());
//            outputStream.flush();
//        }
//
//        // Kiểm tra mã trạng thái HTTP
//        int responseCode = conn.getResponseCode();
//        System.out.println("Response code from ChatGPT: " + responseCode); // Log mã trạng thái
//
//        if (responseCode == HttpURLConnection.HTTP_OK) {
//            // Nhận phản hồi từ ChatGPT
//            BufferedReader in = new BufferedReader(new java.io.InputStreamReader(conn.getInputStream()));
//            StringBuilder response = new StringBuilder();
//            String inputLine;
//
//            while ((inputLine = in.readLine()) != null) {
//                response.append(inputLine);
//            }
//            in.close();
//
//            System.out.println("Response from ChatGPT: " + response.toString()); // Log phản hồi từ ChatGPT
//
//            // Phân tích và lấy nội dung phản hồi
//            JsonObject jsonResponse = JsonParser.parseString(response.toString()).getAsJsonObject();
//            String chatGptReply = jsonResponse.get("choices").getAsJsonArray()
//                    .get(0).getAsJsonObject().get("message").getAsJsonObject()
//                    .get("content").getAsString();
//
//            return chatGptReply; // Trả về nội dung phản hồi từ ChatGPT
//        } else {
//            System.err.println("Error from ChatGPT API: " + responseCode); // Log lỗi từ ChatGPT
//            return "Error: " + responseCode; // Trả về mã lỗi nếu không thành công
//        }
//    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Đọc dữ liệu từ request
        BufferedReader reader = request.getReader();
        StringBuilder jsonBuilder = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            jsonBuilder.append(line);
        }
        String jsonString = jsonBuilder.toString();

        // Chuyển đổi JSON thành đối tượng
        Gson gson = new Gson();
        JsonObject jsonObject = gson.fromJson(jsonString, JsonObject.class);
        String userQuery = jsonObject.getAsJsonObject("queryResult").get("queryText").getAsString();

        // Gọi OpenAI API để nhận phản hồi
        String chatGptResponse = callOpenAIAPI(userQuery);

        // Tạo phản hồi cho Dialogflow
        JsonObject fulfillmentResponse = new JsonObject();
        JsonArray messages = new JsonArray();
        JsonObject message = new JsonObject();
        message.addProperty("text", chatGptResponse);
        messages.add(message);
        fulfillmentResponse.add("fulfillmentMessages", messages);

        // Trả về phản hồi
        response.setContentType("application/json");
        response.getWriter().write(fulfillmentResponse.toString());
    }

    public String callOpenAIAPI(String userMessage) throws IOException {
        String apiKey = ""; //sk-XOQcoPNuor1AVdWcGt8-ciw4TlU_n0aVOjFKt18DDVT3BlbkFJkg8BcK0IuYG1dz7jKPa3Qsdq3Eqb9e3w-RcNBiVCEA
        URL url = new URL("https://api.openai.com/v1/chat/completions");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Authorization", "Bearer " + apiKey);
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setDoOutput(true);

        // JSON body
        String jsonInputString = "{\"model\":\"gpt-3.5-turbo\", \"messages\":[{\"role\":\"user\", \"content\":\"" + userMessage + "\"}]}";

        // Gửi yêu cầu
        try (OutputStream os = connection.getOutputStream()) {
            byte[] input = jsonInputString.getBytes("utf-8");
            os.write(input, 0, input.length);
        }

        // Đọc phản hồi
        try (BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"))) {
            StringBuilder response = new StringBuilder();
            String responseLine;
            while ((responseLine = br.readLine()) != null) {
                response.append(responseLine.trim());
            }
            System.out.println(response.toString());
        }
        return null;
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
