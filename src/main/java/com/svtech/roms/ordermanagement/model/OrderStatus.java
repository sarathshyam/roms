package com.svtech.roms.ordermanagement.model;

public enum OrderStatus {
	/**
	 * NEW- Order placed in order queue
	 * READY- finished and ready to be served
	 * BILLED- Order billed, no longer needed in order queue
	 * CANCELLED- order cancelled
	 */
  NEW,READY,BILLED,CANCELLED
}
