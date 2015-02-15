package com.artemis.io;

import org.junit.Test;

import java.io.InputStream;
import java.io.OutputStream;

import static org.junit.Assert.*;

public class WorldModelTest {

	@Test
	public void intra_section_wiring_test() {
		WorldModel wm = WorldModel.create()
				.add(SectionA.class)
				.add(SectionB.class)
				.build();

		assertEquals(SectionB.class, wm.sections.get(1).getClass());
		SectionB sectionB = (SectionB) wm.sections.get(1);
		assertNotNull(sectionB.sectionA);
	}

	@Test
	public void initialization_test() {
		WorldModel wm = WorldModel.create()
				.add(SectionA.class)
				.build();

		assertEquals(SectionA.class, wm.sections.get(0).getClass());
		SectionA sec = (SectionA) wm.sections.get(0);
		assertTrue(sec.initialized);
	}

	public static class SectionA extends Section {
		boolean initialized;

		@Override
		protected void initialize() {
			initialized = true;
		}

		@Override
		protected void read(InputStream is) {}
		@Override
		protected void write(OutputStream is) {}
	}

	public static class SectionB extends Section {
		SectionA sectionA;

		@Override
		protected void initialize() {}
		@Override
		protected void read(InputStream is) {}
		@Override
		protected void write(OutputStream is) {}
	}
}