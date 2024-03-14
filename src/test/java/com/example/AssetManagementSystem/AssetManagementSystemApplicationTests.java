package com.example.AssetManagementSystem;

import com.example.AssetManagementSystem.controller.CategoryControllerTest;
import com.example.AssetManagementSystem.controller.AssetControllerTest;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class AssetManagementSystemApplicationTests {

	public static void main(String[] args) {
		Result result = JUnitCore.runClasses(
				CategoryControllerTest.class,
				AssetControllerTest.class
		);

		for (Failure failure : result.getFailures()) {
			System.out.println(failure.toString());
		}

		if (result.wasSuccessful()) {
			System.out.println("All tests passed successfully!");
		} else {
			System.out.println("Some tests failed. Check the output for details.");
		}
	}
}