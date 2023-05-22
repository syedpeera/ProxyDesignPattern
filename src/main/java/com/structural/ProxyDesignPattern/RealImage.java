package com.structural.ProxyDesignPattern;

public class RealImage implements Image{
	private String fileName;
	
	public RealImage(String fileName) {
		this.fileName=fileName;
		loadFromDisk();
	}
	public void loadFromDisk() {
		System.out.println("Loading Image from Disk..."+fileName);
	}
	@Override
	public void display() {
		System.out.println("Displaying Image: "+fileName);
	}
}
