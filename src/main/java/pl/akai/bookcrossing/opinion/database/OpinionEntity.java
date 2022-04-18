package pl.akai.bookcrossing.opinion.database;

import lombok.Data;
import lombok.NoArgsConstructor;
import pl.akai.bookcrossing.common.database.ResourceEntity;
import pl.akai.bookcrossing.user.database.UserEntity;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
@Table(name = "opinion")
public class OpinionEntity {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="resource_id", nullable=false)
    private ResourceEntity resource;

    @Column(name = "rating", nullable = false)
    private double rating;

    @Column(name = "content")
    private String content;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="author_id", nullable=false)
    private UserEntity author;
}
