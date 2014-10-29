/**
*	Name			: Yong Shan Xian
*	Matric-number	: A0132763H
*	Plab account	: plab0291
*/

import java.util.*;

class Person {
	private static ArrayList<Person> allPersons;

	private String name;
	private ArrayList<Group> groupList;

	private Person(String _name){
		name = _name;
		groupList = new ArrayList<Group>();
	}

	public static void addPerson(String _name){
		if (allPersons == null)
			allPersons = new ArrayList<Person>();
		allPersons.add(new Person(_name));
	}

	public static Person getPerson(String name){
		for (int i = 0; i < allPersons.size(); ++i){
			if (allPersons.get(i).getName().equals(name))
				return allPersons.get(i);
		}
		return null;
	}

	public String getName(){
		return name;
	}

	public ArrayList<Group> getGroupList(){
		return groupList;
	}

	public void addToGroup(Group addToGroup){
		addToGroup.getMemberList().add(this);
		groupList.add(addToGroup);
	}

	public boolean friendWith(Person check){
		if(check != this) { // you also not friend with yourself lah
			for (Group group: groupList) {
				if(group.getMemberList().contains(check)){
					return true;
				}
			}
		}
		return false;
	}

	public boolean friendOfFriendWith(Person check){
		if (check != this) { // first of all, you are not FOF with yourself
			for (Group group: groupList) {
				for (Person friend: group.getMembersExcept(this)) {
					if(friend.friendWith(check) && !this.friendWith(check)) {
						return true;
					}
				}
			}
		}
		return false;
	}	

	public int numberOfFriends(){
		HashSet<Person> friends = getFriends();
		return friends.size();
	}

	private HashSet<Person> getFriends()
	{
		HashSet<Person> friends = new HashSet<Person>();
		for (Group group: groupList) {
			for(Person friend: group.getMembersExcept(this)){
				friends.add(friend);
			}
		}
		return friends;
	}

	public int numberOfFriendOfFriends(){
		HashSet<Person> friendOfFriends = new HashSet<Person>();
		for (Group group: groupList) {
			// get all friends from the group, don't add yourself!
			for (Person friend: group.getMembersExcept(this)) {
				for (Group friendGroup: friend.groupList) {
					for (Person friendOfFriend: friendGroup.getMembersExcept(friend)) {
						// make sure you not friend with this guy, and this guy is not yourself
						if(!this.friendWith(friendOfFriend) && friendOfFriend != this){
							friendOfFriends.add(friendOfFriend);
						}
					}
				}
			}
		}
		return friendOfFriends.size();
	}
}

class Group {
	private static ArrayList<Group> allGroup;

	private String name;
	private ArrayList<Person> memberList;

	private Group(String _name){
		name = _name;
		memberList = new ArrayList<Person>();
	}

	public String getName(){
		return name;
	}

	public ArrayList<Person> getMemberList(){
		return memberList;
	}

	/**
	 * Get all the members, except this one person which we do not like.
	 */
	public ArrayList<Person> getMembersExcept(Person person)
	{
		ArrayList<Person> members = new ArrayList<Person>();
		for (Person member: memberList) {
			if(member == person){
				continue;
			}
			members.add(member);
		}
		return members;
	}

	public static void addGroup(String _name){
		if (allGroup == null) {
			allGroup = new ArrayList<Group>();
		}
		allGroup.add(new Group(_name));
	}
	
	public static Group getGroup(String name) {
		for (Group group: allGroup) {
			if (group.getName().equals(name)) {
				return group;
			}
		}
		return null;
	}
}

public class Friends
{
	public static void main(String[] args){
		Scanner in = new Scanner(System.in);
		buildData(in);

		int queryCount = in.nextInt();
		for (int c = 0; c < queryCount; ++c) {
			runQuery(in);
		}
	}

	/**
	 * Build the data from the input
	 * Pre-Condition: none.
	 * Post-Condition: Can start running queries liao lah.
	 */
	public static void buildData(Scanner in)
	{
		int countPeople = in.nextInt();
		int countGroups = in.nextInt();
		in.nextLine();

		for (int i = 0; i < countPeople; ++i) {
			String personName = in.nextLine();
			Person.addPerson(personName);
		}

		for (int j = 0; j < countGroups; ++j) {
			String groupName = in.nextLine();
			Group.addGroup(groupName);
		}
	}

	/**
	 * Perform a single query and determine the operation to run.
	 * Pre-Condition: The input data must have already been initialised.
	 * Post-Condition: none.
	 */
	public static void runQuery(Scanner in)
	{
		int type = in.nextInt();
		switch (type) {
			case 1: /* Add Person to Group */
				addPersonToGroup(in);
				break;

			case 2: /* Check Person A and B are friends or not */
				checkFriends(in);
				break;

			case 3: /* Count friends of Person */
				countFriends(in);
				break;

			case 4: /* Check friends of friends */
				checkFriendOfFriend(in);
				break;

			case 5: /* Count numbers of friend of friend */
				countFriendOfFriend(in);
				break;
		}
	}

	/**
	 * Perform a query to add a person into the group
	 * Pre-Condition: The query type must be deteremined already before calling this method.
	 *				  The input data must has already been initialised.
	 * Post-Condition: The person has been added to the group
	 */
	public static void addPersonToGroup(Scanner in)
	{
		String personName = in.next();
		String groupName = in.next();
		Person person = Person.getPerson(personName);
		Group group = Group.getGroup(groupName);
		person.addToGroup(group);
	}

	/**
	 * Perform a query to check if two names given are friends
	 * Pre-Condition: The query type must be determined.
	 *				  The input data must have already been initialised.
	 * Post-Condition: The method will output YES or NO depending on whether the two names given are friends.
	 */
	public static void checkFriends(Scanner in)
	{
		String personAName = in.next();
		String personBName = in.next();

		Person personA = Person.getPerson(personAName);
		Person personB = Person.getPerson(personBName);

		boolean areFriends = personA.friendWith(personB);
		System.out.println(areFriends ? "YES" : "NO");
	}

	/**
	 * Perform a query to count the number of friends of a given person.
	 * Pre-Condition: The query type must be determined.
	 *				  The input data must have already been initialised.
	 * Post-Condition: The method will print out the number of friends of the given person.
	 */
	public static void countFriends(Scanner in)
	{
		String personName = in.next();
		Person person = Person.getPerson(personName);
		System.out.println(person.numberOfFriends());
	}

	/**
	 * Perform a query to check if two given names are friend of friend
	 * Pre-Condition: The query type must be determined.
	 * 				  The input data must have already been initialised.
	 * Post-Condition: The method will print YES or NO depending on whether the two given names are friend of friend or not.
	 */
	public static void checkFriendOfFriend(Scanner in)
	{
		String personAName = in.next();
		String personBName = in.next();

		Person personA = Person.getPerson(personAName);
		Person personB = Person.getPerson(personBName);

		boolean isFriendOfFriend = personA.friendOfFriendWith(personB);
		System.out.println(isFriendOfFriend ? "YES" : "NO");
	}

	/**
	 * Perform a query to count the nubmer of friends of friends of a given person.
	 * Pre-Condition: The query type must be determined.
	 *				  The input data must have already been initialised.
	 * Post-Condition: The method will print the number of friend of friend of the given person.
	 */
	public static void countFriendOfFriend(Scanner in)
	{
		String personName = in.next();
		Person person = Person.getPerson(personName);
		System.out.println(person.numberOfFriendOfFriends());
	}
}
