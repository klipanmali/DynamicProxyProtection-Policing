
public class PersonImpl implements IPerson {
	private String name;
	private int rating = 0;
	private int ratingCount = 0;
	
	public PersonImpl(String name, int rating){
		this.name = name;
		this.rating = rating;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public int getHotOrNotRating() {
		if (ratingCount == 0) return 0;
		return (rating/ratingCount);
	}

	@Override
	public void setName(String name) {
		this.name = name;

	}

	@Override
	public void setRating(int rating) {
		this.rating += rating;
		ratingCount ++;

	}

}
