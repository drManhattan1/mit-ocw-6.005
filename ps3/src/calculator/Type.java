package calculator;

/*
 * TODO define your symbols and groups from problem 1 here
 */

/**
 * Token type.
 * 
 * The following is a list of the token types contained in this class.
 * They are broken into arbitrary categories in this comment of the form
 * category: tokenType1,tokenType2,...
 * 
 * numbers: number
 * units: in,pt
 * operators: +,-,*,/
 * parentheses: ),(
 * 
 * 
 */
enum Type {
	NUMBER, IN, PT, PLUS, MINUS, MULTIPLY, DIVIDE, OPEN_PARENTHESIS, CLOSE_PARENTHESIS
}