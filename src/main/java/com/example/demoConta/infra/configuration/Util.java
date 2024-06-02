package com.example.demo.infra.configuration;

//import com.portoitapoa.faturamento.security.model.UsuarioContext;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;

/**
 *
 * @author Dionízio Inácio
 */
@Service
@Configuration
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class Util {

    public static final ZoneId ZONA_ID = ZoneId.of("America/Sao_Paulo");

    public static final DateTimeFormatter FORMATER_DD_MM_YYYY_HH_MM_SS = DateTimeFormatter.ofPattern(
        "dd/MM/yyyy - HH:mm:ss");

    public static String LOG_PREFIX;

    public static LocalDateTime getDataAtual() {
        return LocalDateTime.now(ZONA_ID);
    }

    public static LocalDate getLocalDateOrNull(final LocalDateTime date) {
        if (date == null) {
            return null;
        }
        return date.toLocalDate();
    }

    @Value("${server.undertow.accesslog.prefix}")
    public void setPrefix(final String prefix) {
        LOG_PREFIX = prefix;
    }

    /**
     * @return Informações do usuário logado
     */
//    public static UsuarioContext getUsuarioCorrente() {
//        return (UsuarioContext) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//    }
//
//    public static Authentication getAuthentication() {
//        return (Authentication) SecurityContextHolder.getContext().getAuthentication();
//    }


    /**
     * Compara a diferença entre duas datas em String e devole os dias
     *
     * @param startPeriod
     * @param endPeriod
     * @return
     */
    public static Long diffDatesInDays(final LocalDateTime startPeriod, final LocalDateTime endPeriod) {
        Duration diff = Duration.between(startPeriod, endPeriod);
        return diff.toDays();
    }

    public static String dataPorExtenso(final Date date) {
        final DateFormat formatador = DateFormat.getDateInstance(DateFormat.FULL, new Locale("pt", "BR"));

        return formatador.format(date);
    }
}
