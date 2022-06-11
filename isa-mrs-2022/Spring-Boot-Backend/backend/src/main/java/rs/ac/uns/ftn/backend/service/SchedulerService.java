package rs.ac.uns.ftn.backend.service;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import rs.ac.uns.ftn.backend.model.MyUser;
import rs.ac.uns.ftn.backend.repository.AdminRepository;
import rs.ac.uns.ftn.backend.repository.MyUserRepository;

import java.util.List;

@Transactional
@Service
@Slf4j
@AllArgsConstructor
@NoArgsConstructor
public class SchedulerService {

    @Autowired
    private MyUserRepository mur;

    //@Scheduled(cron = "@monthly")
    @Scheduled(cron="0 0 0 1 1/1 *")
    public void doSomething() {
        // something that should execute on 1st day every month @ 00:00

        List<MyUser> myUserList = mur.findAll();

        myUserList.stream().forEach(us-> us.setPenaltyPoint(0l));

        mur.saveAll(myUserList);
    }

}
