package cloneproject.Instagram.domain.feed.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import cloneproject.Instagram.global.vo.Image;

@Getter
@Entity
@Table(name = "post_images")
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PostImage {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "post_image_id")
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "post_id")
	private Post post;

	@Embedded
	@AttributeOverrides({
		@AttributeOverride(name = "imageUrl", column = @Column(name = "post_image_url")),
		@AttributeOverride(name = "imageType", column = @Column(name = "post_image_type")),
		@AttributeOverride(name = "imageName", column = @Column(name = "post_image_name")),
		@AttributeOverride(name = "imageUUID", column = @Column(name = "post_image_uuid"))
	})
	private Image image;

	@Column(name = "post_image_alt_text")
	private String altText;

	@OneToMany(mappedBy = "postImage", orphanRemoval = true)
	private List<PostTag> postTags = new ArrayList<>();

	@Builder
	public PostImage(Post post, Image image, String altText) {
		this.post = post;
		this.image = image;
		this.altText = altText;
	}

}
