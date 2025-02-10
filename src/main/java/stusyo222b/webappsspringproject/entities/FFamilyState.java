package stusyo222b.webappsspringproject.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Check;
import org.hibernate.annotations.ColumnDefault;
import stusyo222b.webappsspringproject.enums.OfficeWorkerFamilyState;
import stusyo222b.webappsspringproject.enums.OfficeWorkerFamilyStateConverter;

@Entity
@Table(name = "ffamilystates")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FFamilyState {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_ff")
    private Long id;

    @OneToOne
    @JoinColumn(name = "id_officeworker", nullable = false, updatable = false, unique = true)
    private OfficeWorker worker;

    @Enumerated(EnumType.STRING)
    @Convert(converter = OfficeWorkerFamilyStateConverter.class)
    @Column(name = "officeworker_famState", nullable = false)
    private OfficeWorkerFamilyState officeWorkerFamilyState;

    @Column(name = "num_child", nullable = false)
    @Check(constraints = "num_child = 0 || num_child>0")
    private int numChild;

    @Column(name = "num_little_child", nullable = false)
    @Check(constraints = "num_little_child <= num_child")
    private int numLittleChild;

    @Column(name = "is_apart_owner", nullable = false)
    @ColumnDefault(value = "FALSE")
    private boolean isApartOwner;


    public String toStringBase() {
        return "FfamilyState: " + officeWorkerFamilyState +
                ", has number of children: " + numChild +
                ", from it: " + numLittleChild + " under 18"+
                ", owns an apartment: " + (isApartOwner ? "yes" : "no");
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("FFamilyState{");
        sb.append("id=").append(id);
        sb.append(", family state=").append(officeWorkerFamilyState);
        sb.append(", number of children=").append(numChild);
        sb.append(", number of children under 18 =").append(numLittleChild);
        sb.append(", owns an apartment=").append(isApartOwner ? "yes" : "no");
        if (worker != null) {
            sb.append(", worker=").append(worker.getSurname());
        } else {
            sb.append(", worker=not defined");
        }
        sb.append('}');
        return sb.toString();
    }
}
