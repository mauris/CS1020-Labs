/**
 * name      : Yong Shan Xian
 * matric no.: A0132763H
 */

import java.util.*;

class Author
{
	private String _name;
	private static ArrayList<Author> allAuthors;

	private Author(String pName)
	{
		this._name = pName;
	}

	/**
	 *      findOrCreateAuthor: Find the Author object by the author's name or create a new author object if it does not exists.
	 *      Pre-condition : The name of the author must be non-empty.
	 *      Post-condition: The Author object is returned. If the allAuthors array list was not created, this method will create it.
	 */
	public static Author findOrCreateAuthor(String pName)
	{
		if (allAuthors == null) {
			allAuthors = new ArrayList<Author>();
		}
		int n = allAuthors.size();

		for (int i = 0; i < n; ++i) {
			if (allAuthors.get(i).getName().equals(pName)) {
				return allAuthors.get(i);
			}
		}
		Author author = new Author(pName);
		allAuthors.add(author);
		return author;
	}

	public String getName()
	{
		return _name;
	}

	public static ArrayList<Author> getAllAuthors()
	{
		return allAuthors;
	}

}


class Article
{
	private String _name;
	private ArrayList<Author> mCoAuthorList;

	public Article(String name)
	{
		this._name = name;
		mCoAuthorList = new ArrayList<Author>();
	}

	public String getName()
	{
		return _name;
	}

	/**
	 *      hasAuthor: Checks if this article was written by a particular author
	 *      Pre-condition : The author parameter must not be null.
	 *      Post-condition: Returns true if the author has coauthored this article and false otherwise.
	 */
	public boolean hasAuthor(Author author)
	{
		return mCoAuthorList.contains(author);
	}

	public ArrayList<Author> getCoAuthorList()
	{
		return mCoAuthorList;
	}

	public void addCoAuthor(Author author)
	{
		mCoAuthorList.add(author);
	}
}

class Coauthorship
{
	static Article[] articles;

	public static void main(String[]args)
	{
		Scanner sc = new Scanner(System.in);
		buildArticles(sc);
		queryCoAuthors(sc);
	}

	/**
	 *      buildArticles: Build the articles' data based on input from the user.
	 *      Pre-condition : A Scanner object must have already been created.
	 *      Post-condition: The articles array will be initialized based on the input from the user.
	 */
	public static void buildArticles(Scanner sc)
	{
		int articlesCount = sc.nextInt();
		articles = new Article[articlesCount];
		for (int i = 0; i < articlesCount; ++i) {
			String articleName = sc.next();
			int authorsCount = sc.nextInt();
			Article article = new Article(articleName);
			for (int j = 0; j < authorsCount; ++j) {
				String authorName = sc.next();
				Author author = Author.findOrCreateAuthor(authorName);
				article.addCoAuthor(author);
			}
			articles[i] = article;
		}
	}

	/**
	 *      queryCoAuthors: Allow the user to query the number of co-authors for a given author.
	 *      Pre-condition : A Scanner object must have already been created.
	 *      				The articles object must have already been initialised.
	 *      Post-condition: The method will print the author names and their respective number of coauthors.
	 */
	public static void queryCoAuthors(Scanner sc)
	{
		int queryCount = sc.nextInt();
		sc.nextLine();
		for (int i = 0; i < queryCount; ++i) {
			String authorName = sc.nextLine();
			Author author = Author.findOrCreateAuthor(authorName);
			int coauthors = countCoAuthors(author);
			System.out.println(authorName + " " + coauthors);
		}
	}

	/**
	 *      countCoAuthors: Count the number of coauthors for a given author
	 *      Pre-condition : The articles object must have already been initialised.
	 *      Post-condition: Returns the number of coauthors
	 */
	private static int countCoAuthors(Author author)
	{
		HashSet<Author> coAuthors = new HashSet<Author>();
		for (Article article : articles) {
			if (article.hasAuthor(author)) {
				for(Author coauthor : article.getCoAuthorList()) {
					if (coauthor == author) {
						continue;
					}
					coAuthors.add(coauthor);
				}
			}
		}
		return coAuthors.size();
	}
}
