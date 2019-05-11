package bupt.coder.notice;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

import java.text.SimpleDateFormat;
import java.util.Date;

@SpringBootApplication
public class NoticeApplication {

    @Bean
    @Profile("create")
    CommandLineRunner init(NoticeRepository noticeRepository) {
        return args -> {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date date = sdf.parse("2019-05-11");
            Notice notice = new Notice("搞毕设好累啊", date, "TheShy");
            Notice notice2 = new Notice("突然感觉自己不能适应996了", date, "Facker");
            Notice notice3 = new Notice("好想进体制内", date, "Uzi");
            Notice notice4 = new Notice("体制内少钱呀", date, "Doinb");
            Notice notice5 = new Notice("感觉生命更重要", date, "Rookie");
            noticeRepository.save(notice);
            noticeRepository.save(notice2);
            noticeRepository.save(notice3);
            noticeRepository.save(notice4);
            noticeRepository.save(notice5);
        };
    }

    public static void main(String[] args) {
        SpringApplication.run(NoticeApplication.class, args);
    }

}
