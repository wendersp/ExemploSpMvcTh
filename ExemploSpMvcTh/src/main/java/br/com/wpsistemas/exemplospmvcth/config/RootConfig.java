/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.wpsistemas.exemplospmvcth.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 *
 * @author wender
 */
@Configuration
@ComponentScan("br.com.wpsistemas.exemplospmvcth")
@EnableWebMvc
public class RootConfig {
    
}
