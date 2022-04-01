package com.revature.cachemoney.backend.beans.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Properties;

import static org.mockito.Mockito.*;

class EmailUtilTest {
    @Mock
    EmailUtil emailUtil1;
    @Mock
    Properties properties;
    //Field session of type Session - was not mocked since Mockito doesn't mock a Final class when 'mock-maker-inline' option is not set
    @InjectMocks
    EmailUtil emailUtil;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testGetInstance() {
        EmailUtil result = EmailUtil.getInstance();
        result.setProperties(properties);
        result.setHost("smtp.gmail.com");
        result.setEmailAcc("cachemoneyapp@gmail.com");
        result.setEmailPass("5FSqN!tifr#mqo5P");
         result.setSession(emailUtil.getSession());

        Assertions.assertEquals(emailUtil, result);
    }

    @Test
    void testSendEmail() {
        //when(EmailUtil.getInstance()).thenReturn(null);
        when(emailUtil1.getEmailAcc()).thenReturn("getEmailAccResponse");
        when(emailUtil1.getSession()).thenReturn(null);

        emailUtil.sendEmail("recipient", "subject", "body");
    }

    @Test
    void testSetProperties() {
        emailUtil.setProperties(null);
    }

    @Test
    void testSetHost() {
        emailUtil.setHost("host");
    }

    @Test
    void testSetEmailAcc() {
        emailUtil.setEmailAcc("emailAcc");
    }

    @Test
    void testSetEmailPass() {
        emailUtil.setEmailPass("emailPass");
    }

    @Test
    void testSetSession() {
        emailUtil.setSession(null);
    }

    @Test
    void testEquals() {
       //EmailUtil emailUtil2 = new EmailUtil(properties, "smtp.gmail.com", "cachemoneyapp@gmail.com", "5FSqN!tifr#mqo5P", "javax.mail.Session@5cde6747");
        when(emailUtil1.getProperties()).thenReturn(null);
        when(emailUtil1.getHost()).thenReturn("smtp.gmail.com");
        when(emailUtil1.getEmailAcc()).thenReturn("cachemoneyapp@gmail.com");
        when(emailUtil1.getEmailPass()).thenReturn("5FSqN!tifr#mqo5P");
        when(emailUtil1.getSession()).thenReturn(null);
        when(emailUtil1.canEqual(any())).thenReturn(true);

        emailUtil.setProperties(null);
        emailUtil.setSession(null);

        boolean result = emailUtil.equals(emailUtil1);

        Assertions.assertTrue(result);
    }


    @Test
    void testHashCode() {

        int result = emailUtil1.hashCode();
        Assertions.assertEquals(emailUtil1.hashCode(), result);
    }

    @Test
    void testToString() {
        when(emailUtil1.getProperties()).thenReturn(null);
        when(emailUtil1.getHost()).thenReturn("getHostResponse");
        when(emailUtil1.getEmailAcc()).thenReturn("getEmailAccResponse");
        when(emailUtil1.getEmailPass()).thenReturn("getEmailPassResponse");
        when(emailUtil1.getSession()).thenReturn(emailUtil.getSession());

        String result = emailUtil.toString();
        Assertions.assertEquals("EmailUtil(properties=properties, host=smtp.gmail.com, emailAcc=cachemoneyapp@gmail.com, emailPass=5FSqN!tifr#mqo5P, session=javax.mail.Session@52851b44)", result);
    }
}

