package generator;

import java.util.Random;

public class RandomGenerator {
	private static Random random = new Random();
	public static class Character implements Generator<java.lang.Character> {
		private char[] chars = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i',
				'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u',
				'v', 'w', 'x', 'y', 'z', 'A', 'B', 'C', 'D', 'E', 'F', 'G',
				'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S',
				'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };
		@Override
		public java.lang.Character next() {
			return chars[random.nextInt(chars.length)];
		}
	}
	public static class String implements Generator<java.lang.String> {
		private int size = 7;
		public String() {
		}
		public String(int size) {
			this.size = size;
		}
		@Override
		public java.lang.String next() {
			StringBuffer sb = new StringBuffer();
			Character character = new Character();
			for (int i = 0; i < size; i++)
				sb.append(character.next());
			return sb.toString();
		}
	}
	public static class Boolean implements Generator<java.lang.Boolean> {
		@Override
		public java.lang.Boolean next() {
			return random.nextBoolean();
		}
	}
	public static class Byte implements Generator<java.lang.Byte> {
		private Integer integer;
		public Byte() {
			integer = new Integer();
		}
		public Byte(int size) {
			integer = new Integer(size);
		}
		@Override
		public java.lang.Byte next() {
			return integer.next().byteValue();
		}
	}
	public static class Short implements Generator<java.lang.Short> {
		private Integer integer;
		public Short() {
			integer = new Integer();
		}
		public Short(int size) {
			integer = new Integer(size);
		}
		@Override
		public java.lang.Short next() {
			return integer.next().shortValue();
		}
	}
	public static class Integer implements Generator<java.lang.Integer> {
		private int size = 100;
		public Integer() {}
		public Integer(int size) {
			this.size = size;
		}
		@Override
		public java.lang.Integer next() {
			return random.nextInt(size);
		}
	}
	public static class Long implements Generator<java.lang.Long> {
		private Integer integer;
		public Long() {
			integer = new Integer();
		}
		public Long(int size) {
			integer = new Integer(size);
		}
		@Override
		public java.lang.Long next() {
			return integer.next().longValue();
		}
	}
	public static class Float implements Generator<java.lang.Float> {
		@Override
		public java.lang.Float next() {
			int round = Math.round(random.nextFloat() * 100);
			return ((float) round) / 100;
		}
	}
	public static class Double implements Generator<java.lang.Double> {
		@Override
		public java.lang.Double next() {
			int round = Math.round(random.nextFloat() * 100);
			return ((double) round) / 100;
		}
	}
}
