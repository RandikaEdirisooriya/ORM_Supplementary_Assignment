7. Define bidirectional one-to-many relationship between Author and Book entities using
@JoinColumn annotation.

  In a bidirectional one-to-many relationship between Author and Book entities using @JoinColumn:

  In the Author entity, use @OneToMany(mappedBy = "author") to establish the relationship, indicating that the mapping is owned by the author field in the Book entity.

  In the Book entity, use @ManyToOne to represent the many-to-one side of the relationship, and @JoinColumn(name = "author_id") to specify the foreign key column (author_id) that links to the Author entity's primary key (id).
