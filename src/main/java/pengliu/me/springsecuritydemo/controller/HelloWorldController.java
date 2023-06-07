package pengliu.me.springsecuritydemo.controller;

import org.springframework.web.bind.annotation.*;
import pengliu.me.springsecuritydemo.ResponseDocument;
import org.springframework.security.access.prepost.PreAuthorize;

@RestController
@RequestMapping("/api/v1")
public class HelloWorldController {

    @GetMapping("/get/test")
    public ResponseDocument<?> myGetTest() {
        return ResponseDocument.emptySuccessResponse();
    }

    @PutMapping("/update/test")
    @PreAuthorize("hasRole('ROLE_ADMIN')") // 指定ADMIN可以访问的接口
    public ResponseDocument<?> myUpdateTest() {
        return ResponseDocument.emptySuccessResponse();
    }

    @PostMapping("/update/student")
    @PreAuthorize("hasAuthority('student:write')") // 指定拥有student:write权限的用户可以访问的接口。其中ADMIN的role是包含此权限的，所有ADMIn可以访问此接口。
    public ResponseDocument<?> studentWrite() {
        return ResponseDocument.emptySuccessResponse();
    }

    @PostMapping("/update/book")
    @PreAuthorize("hasAuthority('book:write')") // 指定拥有book:write权限的用户可以访问的接口。其中ADMIN的role是没有包含此权限的，所有ADMIn不能访问此接口。
    public ResponseDocument<?> bookWrite() {
        return ResponseDocument.emptySuccessResponse();
    }
}
