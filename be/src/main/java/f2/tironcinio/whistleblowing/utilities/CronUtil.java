package f2.tironcinio.whistleblowing.utilities;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;

import f2.tironcinio.whistleblowing.daos.SegnalazioneDao;
import f2.tironcinio.whistleblowing.entities.Segnalazione;
import f2.tironcinio.whistleblowing.responses.Priorita;

@Controller
public class CronUtil {

	@Autowired
	SegnalazioneDao sDao;

	@Scheduled(cron = "0 0 0 * * *")
	public void setPriorityAtMidnight() {

		List<Segnalazione> urgentReports = sDao.findUrgentReports(System.currentTimeMillis());

		urgentReports.stream().forEach(r -> {
			try {
				r.setPriorita(Priorita.Urgente);
				sDao.save(r);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("Segnalazione aggiornata e salvata");
		});
	}

}
