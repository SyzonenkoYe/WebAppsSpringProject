package stusyo222b.webappsspringproject.entities;



import jakarta.persistence.*;
//import jakarta.validation.constraints.Pattern;
//import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.Check;
import org.springframework.format.annotation.DateTimeFormat;
import stusyo222b.webappsspringproject.enums.OfficeWorkerStatus;
import stusyo222b.webappsspringproject.enums.OfficeWorkerStatusConverter;

import java.time.LocalDate;


@Entity
@Table(name = "officeworkers")
@Check(constraints = "end_work >= start_work + INTERVAL 4 MONTH")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class OfficeWorker {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="surname", length = 100,nullable = false)
    @Check(constraints = "REGEXP_LIKE(surname, '^[A-Z][a-z]+$', 'c')= 1")
    private String surname;

    @Column(name="name", length = 100,nullable = false)
    @Check(constraints = "REGEXP_LIKE(name, '^[A-Z][a-z]+$', 'c')= 1")
    private String name;

    @Column(name="pname", length = 100,nullable = false)
    @Check(constraints = "REGEXP_LIKE(pname, '^[A-Z][a-z]+$', 'c')= 1")
    private String pname;

    @Column(name = "start_work", nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate startWork;

    @Column(name = "end_work")
    @DateTimeFormat(pattern = "dd.MM.yyyy")
    private LocalDate endWork;

    @Column(name="worker_cod", nullable=false, unique = true)
    //@Size(min = 4, max = 4, message = "Код працівника має складатися з 4 цифр.")
    //@Pattern(regexp = "^[0-9]{4}$", message = "Код працівника повинен складатися з 4 цифр.")
    private String workerCod;

    @Enumerated(EnumType.STRING)
    @Column(name = "officeworker_status", nullable = false, length = 12)
    @Convert(converter = OfficeWorkerStatusConverter.class)
    private OfficeWorkerStatus officeWorkerStatus;


    // Constructor for create instance for testing
    public OfficeWorker(String surname) {
        this.id = null;
        this.surname = surname;
        this.name = name;
        this.pname = pname;
        this.startWork = startWork;
        this.endWork = endWork;
        this.workerCod = workerCod;
        this.officeWorkerStatus = OfficeWorkerStatus.middle;

    }


}
