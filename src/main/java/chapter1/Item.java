package chapter1;

abstract class Item {
	Long id;
	String name;
	int price;
}

class Album extends Item {
	String srtist;
}

class Movie extends Item {
	String director;
	String actor;
}

class Book extends Item {
	String author;
	String isbn;
}
