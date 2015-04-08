//I checked the Javadoc output and it was OK

/**An instance (i.e. object) maintains information about a bee.*/
public class Bee {

	private String nickname; //name given to this Bee. String of length > 0
	private int yearOfHatching; //year of hatching. Must be >= 2000
	private int monthOfHatching; //month of hatching. In range 1..12 
								// with 1 being January, etc
	private char gender; //gender of this Bee. ‘M’ means male and ‘F’ means
						// female. If the bee is male, it has no father.
	private Bee mother; //Name the object of class Bee that is the mother of 
						// the object. null if unknown
	private Bee father; //Name of the object of class Bee that is the father of
						//this object. null if unknown or this bee is male.
	private int numberOfChildren; //children of this Bee

	/**Constructor: a new Bee with nickname n, hatch year y, hatch month m,
	and gender g. Its parents are unknown, and it has no children. 
	Precondition: n's length is > 0 and y >= 2000. g is either 'M' or 'F'. m 
	in 1..12.*/
	public Bee(String n, int y, int m, char g){
		nickname = n;
		yearOfHatching = y;
		monthOfHatching = m;
		gender = g;
		mother = null;
		father = null;
		numberOfChildren = 0;
	}


	/**Return this Bee's nickname*/
	public String getNickname() {
		return nickname;
	}


	/**Return the year this Bee hatched from its egg*/
	public int getYear() {
		return yearOfHatching;
	}


	/**Return the month this Bee hatched from its egg*/
	public int getMonth() {
		return monthOfHatching;
	}


	/**Return the value of "This Bee is male."*/
	public boolean isMale() {
		return gender=='M';
	}


	/**Return this Bee's mother (null if unknown)*/
	public Bee getMom() {
		return mother;
	}


	/**Return this Bee's father (null if unknown)*/
	public Bee getDad() {
		return father;
	}


	/**Return the number of children of this Bee*/
	public int getNumChildren() {
		return numberOfChildren;
	}

	
	/**Add e as this Bee's mother. Precondition: this Bee’s mother is unknown and 
	e is female.*/
	public void addMom(Bee e){
		mother = e;
		mother.numberOfChildren = mother.getNumChildren()+1;
	}

	/**Add e as this Bee's father. Precondition: This Bee's father is unknown, 
	this Bee is female, and e is male.*/
	public void addDad(Bee e){
		father = e;
		father.numberOfChildren = father.getNumChildren()+1;
	}

	/**Constructor: a male Bee with nickname n, hatch year y, hatch month m, and 
	mother ma. Precondition: n has at least 1 character, y >= 2000, m is in 1..12, 
	and ma is a female.*/
	public Bee(String n, int y, int m, Bee ma){
		assert (n.length() >= 1);
		assert (y >= 2000);
		assert (m>=1 && m<=12);
		assert (ma.isMale() == false);	

		nickname = n;
		yearOfHatching = y;
		monthOfHatching = m;
		mother = ma;
	}

	/**Constructor: a female Bee with nickname n, hatch year y, hatch month m, 
	mother ma, and father pa.  Precondition: n has at least 1 character,
	y >= 2000, m is in 1..12, ma is a female, and pa is a male.*/
	public Bee(String n, int y, int m, Bee ma, Bee pa){
		assert n.length() >= 1;
		assert y >= 2000;
		assert (m>=1 && m<=12);
		assert (ma.isMale() == false);	
		assert pa.isMale();	

		nickname = n;
		yearOfHatching = y;
		monthOfHatching = m;
		mother = ma;
		father = pa;
	}

	/**Return value of "this Bee is younger than e". Precondition: e is not null.*/ 
	boolean isYounger(Bee e){
		assert (e != null);
		return yearOfHatching < e.getYear() || 
				(yearOfHatching == e.yearOfHatching && 
				monthOfHatching < e.monthOfHatching);
	}

	/**Return value of "this Bee and e are siblings".*/
	boolean isSibling(Bee e){
		return e != null && this != e 
			&& ((getMom() != null && getMom() == e.getMom()) 
			|| (getDad() != null && getDad() == e.getDad()));
	}
}