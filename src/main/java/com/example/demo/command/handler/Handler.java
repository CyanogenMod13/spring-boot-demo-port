package com.example.demo.command.handler;

import com.example.demo.command.Command;

public interface Handler<C extends Command, R> {
    R handle(C command);
}
