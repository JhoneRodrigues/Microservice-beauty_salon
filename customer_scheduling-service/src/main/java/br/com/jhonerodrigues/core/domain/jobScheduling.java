package br.com.jhonerodrigues.core.domain;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(of="id")

@Entity
@Table(name = "tb_job_scheduling")
public class jobScheduling {
	
	@EmbeddedId
	private jobSchedulingPK id = new jobSchedulingPK();

	public jobScheduling(Job job, Scheduling scheduling) {
		id.setJob(job);
		id.setScheduling(scheduling);
	}
	
	
}
