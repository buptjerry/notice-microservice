package bupt.coder.notice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Notice")
public class NoticeController {

    @Autowired
    private NoticeRepository noticeRepository;

    @PutMapping
    public void add(@RequestBody Notice notice) {
        noticeRepository.saveAndFlush(notice);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable long id) {
        Notice notice = noticeRepository.findById(id).get();
        noticeRepository.delete(notice);
    }

    @PostMapping("/{id}")
    public void update(@RequestBody Notice notice, @PathVariable long id) {
        Notice old = noticeRepository.findById(id).get();
        old.setAuthor(notice.getAuthor());
        old.setDate(notice.getDate());
        old.setMessage(notice.getMessage());
        noticeRepository.saveAndFlush(old);
    }

    @GetMapping("/{id}")
    public Notice find(@PathVariable long id) {
        return noticeRepository.findById(id).get();
    }


    @GetMapping
    public List<Notice> findAll(){
        return noticeRepository.findAll();
    }
}
