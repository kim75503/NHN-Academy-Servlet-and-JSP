package com.nhnacademy.Filter;

import lombok.Getter;

public class Member {

    @Getter
    private final String id;
    @Getter
    private final String name;
    @Getter
    private final String password;
    private final Role role;

    private Member(String id, String name, String password,  Role role){
        this.id=id;
        this.name=name;
        this.password=password;
        this.role = role;
    }

    public static Member createAdmin(String id, String name, String password){
        return new Member(id,name, password, Role.ADMIN);
    }

    public static Member createUser(String id, String name,String password){
        return new Member(id,name,password,Role.USER);
    }

    public static Member createManager(String id, String name,String password){
        return new Member(id,name,password,Role.MANAGER);
    }

    public static Member createUncertifiedMember(String id, String name ,String password){
        return new Member(id,name,password,Role.NONE);
    }

    public boolean hasRole(Role role){
        return this.role.equals(role);
    }
}
