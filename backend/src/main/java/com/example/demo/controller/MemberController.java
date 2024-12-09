package com.example.demo.controller;

import com.example.demo.entity.Member;
import com.example.demo.service.MemberService;
import com.example.demo.service.OrderService;
import com.example.demo.util.UUIDUtil;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;




@RestController
@CrossOrigin
@RequestMapping("/member")
public class MemberController {
    @Autowired
    private MemberService memberService;
    @Autowired
    private OrderService orderService;

    //search all member
    @GetMapping("/all")
    public ResponseEntity<Map<String,Object>> getAll(HttpServletRequest request){
        Map<String,Object> map=new HashMap<String, Object>();
        try {
            List<Member> memberList = memberService.findAll();
            map.put("memberList",memberList);
        }catch(Exception e){
            e.printStackTrace();
            map.put("message","get member message error!");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(map);
        }
        return ResponseEntity.ok(map);
    }

    //search member according name
    @GetMapping("/name/{name}")
    public ResponseEntity<Map<String,Object>> getByName(HttpServletRequest request,@PathVariable String name){
        Map<String,Object> map=new HashMap<String, Object>();
        try {
            List<Member> memberList = memberService.findByName(name);
            map.put("memberList",memberList);
        }catch(Exception e){
            e.printStackTrace();
            map.put("message","get member message error!");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(map);
        }
        return ResponseEntity.ok(map);
    }

    //adding new member
    @PostMapping("/")
    public ResponseEntity<Map<String,Object>> save(@RequestBody Member member){
        Map<String,Object> map=new HashMap<String, Object>();
        try {
            Member memberByMid = memberService.findByMid(member.getMid());
            if(memberByMid!=null){
                map.put("message","Member Id already exists!");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(map);
            }
            Member member1 = memberService.create(member);
            map.put("message","Save Member Information Successfully!");
            map.put("member",member1);
            return ResponseEntity.ok(map);
        } catch(Exception e){
            e.printStackTrace();
            map.put("message","Save Member Information error!");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(map);
        }
    }

    //edit member info
    @PutMapping("/")
    public ResponseEntity<Map<String,Object>> update(@RequestBody Member member) {
        Map<String,Object> map=new HashMap<String, Object>();
        try {
            boolean update = memberService.update(member);
            if(update){
                map.put("message","Edit Member Information Successfully!");
                return ResponseEntity.ok(map);
            }else{
                map.put("message","Edit Member Information Error!");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(map);
            }
        } catch(Exception e){
            e.printStackTrace();
            map.put("message","Edit Member Information error!");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(map);
        }
    }


    //recharge
    @PutMapping("/{mid}/topUp/{money}")
    public ResponseEntity<Map<String,Object>> update(HttpServletRequest request,@PathVariable String mid,@PathVariable double money){
        Map<String,Object> map=new HashMap<String, Object>();
        try {
            boolean topUp = memberService.topUp(mid, money);
            if(topUp){
                map.put("message","Recharge successfully!");
                return ResponseEntity.ok(map);
            }else{
                map.put("message", "This member was not found.");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(map);
            }
        }catch(Exception e){
            e.printStackTrace();
            map.put("message","Recharge member error!");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(map);
        }
    }

    //delete member
    @DeleteMapping("/{mid}")
    public ResponseEntity<Map<String,Object>> delete(HttpServletRequest request,@PathVariable String mid){
        Map<String,Object> map=new HashMap<String, Object>();
        try {
            orderService.deleteMid(mid);
            memberService.delete(mid);
            map.put("message","Delete member successfully!");
            return ResponseEntity.ok(map);
        }catch(Exception e){
            e.printStackTrace();
            map.put("message","delete member error!");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(map);
        }
    }
}
