package chapter7.상속관계매핑.singletable;

/*@Entity
@DiscriminatorValue("A")*/
public class Album extends Item {
	private String artist;

	public String getArtist() {
		return artist;
	}

	public void setArtist(String artist) {
		this.artist = artist;
	}
}