package com.example.demoConta.testUtil.utils;

//import com.example.demoConta.infra.adapters.security.model.UsuarioContext;
import org.mockito.Mockito;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * @author Dionízio Inácio
 */
public class TestUtils {
//    public static void mockSecurityContextHolder() {
//        final UsuarioContext applicationUser = UsuarioContext.builder()
//            .login("ze")
//            .adm(false)
//            .nome("zé das couve")
//            .email("zedascouve@email.com")
//            .build();
//        final Authentication auth = Mockito.mock(Authentication.class);
//
//        Mockito.when(auth.getPrincipal())
//            .thenReturn(applicationUser);
//
//        final SecurityContext securityContext = Mockito.mock(SecurityContext.class);
//        Mockito.when(securityContext.getAuthentication())
//            .thenReturn(auth);
//        SecurityContextHolder.setStrategyName(SecurityContextHolder.MODE_INHERITABLETHREADLOCAL);
//        SecurityContextHolder.setContext(securityContext);
//    }
}
