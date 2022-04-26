package com.example.MedicalCenter.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/registerPatient").permitAll()
                .antMatchers("/getAllPatients").permitAll()
                .antMatchers("/updatePatient").permitAll()
                .antMatchers("/deletePatient").permitAll()
                .antMatchers("/createResearchProject").permitAll()
                .antMatchers("/getAllResearchProjects").permitAll()
                .antMatchers("/updateResearchProject").permitAll()
                .antMatchers("/deleteResearchProject").permitAll()
                .antMatchers("/addConsent").permitAll()
                .antMatchers("/withdrawConsent").permitAll()
                .antMatchers("/bindPatientWithResearchProject").permitAll()
                .antMatchers("/unbindPatientWithResearchProject").permitAll()
                .antMatchers("/commissionLaboratoryTest").permitAll()
                .antMatchers("/addTestResult").permitAll()
                .antMatchers("/updateTestResult").permitAll()
                .antMatchers("/deleteTestResult").permitAll()
                .anyRequest().authenticated();
    }
}
