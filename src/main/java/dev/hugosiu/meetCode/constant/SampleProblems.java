package dev.hugosiu.meetCode.constant;

import dev.hugosiu.meetCode.dto.ProblemRequestForInitDTO;
import dev.hugosiu.meetCode.model.enumType.Level;

public class SampleProblems {

  public static final ProblemRequestForInitDTO[] PROBLEMS = new ProblemRequestForInitDTO[]{
          ProblemRequestForInitDTO.builder()
                  .title("Two Sum")
                  .description("Given an array of integers `nums` and an `integer` target, return _indices of the two numbers such that they add up to target._\n\nYou may assume that each input would have **exactly one solution**, and you may not use the same element twice.\n\nYou can return the answer in any order.\n\n\n**Example 1:**\n```\nInput: nums = [2,7,11,15], target = 9\nOutput: [0,1]\nExplanation: Because nums[0] + nums[1] == 9, we return [0, 1].\n```\n**Example 2:**\n```\nInput: nums = [3,2,4], target = 6\nOutput: [1,2]\n```\n**Example 3:**\n```\nInput: nums = [3,3], target = 6\nOutput: [0,1]\n```\n\n**Constraints:**\n\n- `2 <= nums.length <= 104`\n- `109 <= nums[i] <= 109`\n- `109 <= target <= 109`\n- **Only one valid answer exists.**\n \n\n###### Follow-up: Can you come up with an algorithm that is less than O(n2) time complexity?")
                  .level(Level.EASY.getValue())
                  .visibility(true)
                  .placeholder("class Solution {\n    public int add(int a, int b) {\n        //TODO \n    } \n    \n    public int minus(int a, int b){\n        //TODO\n    }\n}")
                  .solution("class Solution {\n    public int add(int a, int b) {\n        return a + b; \n    } \n    \n    public int minus(int a, int b){\n        return a - b ;\n    }\n}")
                  .testScript("import org.junit.jupiter.api.DisplayName;\n" +
                          "import org.junit.jupiter.api.Test;\n" +
                          "import org.junit.jupiter.params.ParameterizedTest;\n" +
                          "import org.junit.jupiter.params.provider.CsvSource;\n" +
                          "import static org.junit.jupiter.api.Assertions.assertEquals;\n" +
                          "\n" +
                          "class ProblemTest {\n" +
                          "    private final Solution calculator = new Solution();\n" +
                          "    @Test\n" +
                          "    @DisplayName(\"1 + 2 = 3\")\n" +
                          "    void addsTwoNumbers() {\n" +
                          "        assertEquals(3, calculator.add(1, 2), \"1 + 2 should equal 3\");\n" +
                          "    }\n" +
                          "    @Test\n" +
                          "    @DisplayName(\"3 - 1 = 2\")\n" +
                          "    void minusTwoNumbers() {\n" +
                          "        assertEquals(2, calculator.minus(3, 1), \"3 - 1 should equal 2\");\n" +
                          "    }\n" +
                          "    @ParameterizedTest(name = \"{0} + {1} = {2}\")\n" +
                          "    @CsvSource({\n" +
                          "            \"0,    1,   1\",\n" +
                          "            \"1,    2,   3\",\n" +
                          "            \"49,  51, 100\",\n" +
                          "            \"1,  100, 101\"\n" +
                          "    })\n" +
                          "    void add(int first, int second, int expectedResult) {\n" +
                          "        assertEquals(expectedResult, calculator.add(first, second),\n" +
                          "                () -> first + \" + \" + second + \" should equal \" + expectedResult);\n" +
                          "    }\n" +
                          "}")
                  .build(),

          ProblemRequestForInitDTO.builder()
                  .title("Reverse Integer")
                  .description("Given a signed 32-bit integer `x`, return `x` with its digits reversed. If reversing `x` causes the value to go outside the signed 32-bit integer range `[-231, 231 - 1]`, then return `0`.\n" +
                          "\n" +
                          "**Assume the environment does not allow you to store 64-bit integers (signed or unsigned).**\n" +
                          "\n" +
                          "**Example 1:**\n" +
                          "```\n" +
                          "Input: x = 123\n" +
                          "Output: 321\n" +
                          "```\n" +
                          "**Example 2:**\n" +
                          "```\n" +
                          "Input: x = -123\n" +
                          "Output: -321\n" +
                          "```\n" +
                          "**Example 3:**\n" +
                          "```\n" +
                          "Input: x = 120\n" +
                          "Output: 21\n" +
                          " ```\n" +
                          "\n" +
                          "**Constraints:**\n" +
                          "- `-231 <= x <= 231 - 1`")
                  .level(Level.MEDIUM.getValue())
                  .visibility(true)
                  .placeholder("class Solution {\n    public int reverse(int x){\n        //TODO\n    }\n}")
                  .solution("class Solution {\n" +
                          "    public int reverse(int x) {\n" +
                          "        long reversed = 0; // Use long to handle overflow\n" +
                          "\n" +
                          "        while (x != 0) {\n" +
                          "            int digit = x % 10; // Get the last digit\n" +
                          "            reversed = reversed * 10 + digit; // Append digit to reversed number\n" +
                          "            x /= 10; // Remove the last digit from x\n" +
                          "            \n" +
                          "            // Check for overflow\n" +
                          "            if (reversed > Integer.MAX_VALUE || reversed < Integer.MIN_VALUE) {\n" +
                          "                return 0; // Return 0 in case of overflow\n" +
                          "            }\n" +
                          "        }\n" +
                          "\n" +
                          "        return (int) reversed; // Cast back to int before returning\n" +
                          "    }\n" +
                          "}")
                  .testScript("import org.junit.jupiter.api.Test;\n" +
                          "import static org.junit.jupiter.api.Assertions.assertEquals;\n" +
                          "\n" +
                          "class ProblemTest {\n" +
                          "    private final Solution solution = new Solution();\n" +
                          "    @Test\n" +
                          "    void testReverse() {\n" +
                          "        assertEquals(321, solution.reverse(123));\n" +
                          "    }\n" +
                          "}")
                  .build(),

          ProblemRequestForInitDTO.builder()
                  .title("Palindrome Number")
                  .description("Determine whether an integer is a palindrome.")
                  .level(Level.HARD.getValue())
                  .visibility(true)
                  .placeholder("class Solution {\n    \n}")
                  .solution("class Solution {\n    public boolean isPalindrome(int x) {\n        //TODO\n    }\n}")
                  .testScript("import org.junit.jupiter.api.Test;\n" +
                          "import static org.junit.jupiter.api.Assertions.assertTrue;\n" +
                          "\n" +
                          "class ProblemTest {\n" +
                          "    private final Solution solution = new Solution();\n" +
                          "    @Test\n" +
                          "    void testIsPalindrome() {\n" +
                          "        assertTrue(solution.isPalindrome(121));\n" +
                          "    }\n" +
                          "}")
                  .build(),

          ProblemRequestForInitDTO.builder()
                  .title("Merge Two Sorted Lists")
                  .description("Merge two sorted linked lists and return it as a new sorted list.")
                  .level(Level.EASY.getValue())
                  .visibility(true)
                  .placeholder("class Solution {\n\n}")
                  .solution("class Solution {public ListNode mergeTwoLists(ListNode l1, ListNode l2) { /* implementation */ }}")
                  .testScript("import org.junit.jupiter.api.Test;\n" +
                          "import static org.junit.jupiter.api.Assertions.assertEquals;\n" +
                          "\n" +
                          "class ProblemTest {\n" +
                          "    private final Solution solution = new Solution();\n" +
                          "    @Test\n" +
                          "    void testMerge() {\n" +
                          "        // test merging logic\n" +
                          "    }\n" +
                          "}")
                  .build(),

          ProblemRequestForInitDTO.builder()
                  .title("Best Time to Buy and Sell Stock")
                  .description("Find the maximum profit you can achieve from this transaction.")
                  .level(Level.EASY.getValue())
                  .visibility(true)
                  .placeholder("class Solution {\n\n}")
                  .solution("class Solution {public int maxProfit(int[] prices) { /* implementation */ }}")
                  .testScript("import org.junit.jupiter.api.Test;\n" +
                          "import static org.junit.jupiter.api.Assertions.assertEquals;\n" +
                          "\n" +
                          "class ProblemTest {\n" +
                          "    private final Solution solution = new Solution();\n" +
                          "    @Test\n" +
                          "    void testMaxProfit() {\n" +
                          "        assertEquals(5, solution.maxProfit(new int[]{7,1,5,3,6,4}));\n" +
                          "    }\n" +
                          "}")
                  .build(),

          ProblemRequestForInitDTO.builder()
                  .title("Valid Parentheses")
                  .description("Determine if the input string is valid.")
                  .level(Level.EASY.getValue())
                  .visibility(true)
                  .placeholder("class Solution {\n\n}")
                  .solution("class Solution {public boolean isValid(String s) { /* implementation */ }}")
                  .testScript("import org.junit.jupiter.api.Test;\n" +
                          "import static org.junit.jupiter.api.Assertions.assertTrue;\n" +
                          "\n" +
                          "class ProblemTest {\n" +
                          "    private final Solution solution = new Solution();\n" +
                          "    @Test\n" +
                          "    void testIsValid() {\n" +
                          "        assertTrue(solution.isValid(\"(){}[]\"));\n" +
                          "    }\n" +
                          "}")
                  .build(),

          ProblemRequestForInitDTO.builder()
                  .title("Remove Duplicates from Sorted Array")
                  .description("Remove duplicates in place from a sorted array.")
                  .level(Level.EASY.getValue())
                  .visibility(true)
                  .placeholder("class Solution {\n\n}")
                  .solution("class Solution {public int removeDuplicates(int[] nums) { /* implementation */ }}")
                  .testScript("import org.junit.jupiter.api.Test;\n" +
                          "import static org.junit.jupiter.api.Assertions.assertEquals;\n" +
                          "\n" +
                          "class ProblemTest {\n" +
                          "    private final Solution solution = new Solution();\n" +
                          "    @Test\n" +
                          "    void testRemoveDuplicates() {\n" +
                          "        assertEquals(2, solution.removeDuplicates(new int[]{1,1,2}));\n" +
                          "    }\n" +
                          "}")
                  .build(),

          ProblemRequestForInitDTO.builder()
                  .title("Implement strStr()")
                  .description("Return the index of the first occurrence of needle in haystack.")
                  .level(Level.EASY.getValue())
                  .visibility(true)
                  .placeholder("class Solution {\n\n}")
                  .solution("class Solution {public int strStr(String haystack, String needle) { /* implementation */ }}")
                  .testScript("import org.junit.jupiter.api.Test;\n" +
                          "import static org.junit.jupiter.api.Assertions.assertEquals;\n" +
                          "\n" +
                          "class ProblemTest {\n" +
                          "    private final Solution solution = new Solution();\n" +
                          "    @Test\n" +
                          "    void testStrStr() {\n" +
                          "        assertEquals(2, solution.strStr(\"hello\", \"ll\"));\n" +
                          "    }\n" +
                          "}")
                  .build(),

          ProblemRequestForInitDTO.builder()
                  .title("Count and Say")
                  .description("The count-and-say sequence is defined starting with '1'.")
                  .level(Level.EASY.getValue())
                  .visibility(true)
                  .placeholder("class Solution {\n\n}")
                  .solution("class Solution {public String countAndSay(int n) { /* implementation */ }}")
                  .testScript("import org.junit.jupiter.api.Test;\n" +
                          "import static org.junit.jupiter.api.Assertions.assertEquals;\n" +
                          "\n" +
                          "class ProblemTest {\n" +
                          "    private final Solution solution = new Solution();\n" +
                          "    @Test\n" +
                          "    void testCountAndSay() {\n" +
                          "        assertEquals(\"11\", solution.countAndSay(2));\n" +
                          "    }\n" +
                          "}")
                  .build(),

          ProblemRequestForInitDTO.builder()
                  .title("Maximum Subarray")
                  .description("Find the contiguous subarray with the largest sum.")
                  .level(Level.EASY.getValue())
                  .visibility(true)
                  .placeholder("class Solution {\n\n}")
                  .solution("class Solution {public int maxSubArray(int[] nums) { /* implementation */ }}")
                  .testScript("import org.junit.jupiter.api.Test;\n" +
                          "import static org.junit.jupiter.api.Assertions.assertEquals;\n" +
                          "\n" +
                          "class ProblemTest {\n" +
                          "    private final Solution solution = new Solution();\n" +
                          "    @Test\n" +
                          "    void testMaxSubArray() {\n" +
                          "        assertEquals(6, solution.maxSubArray(new int[]{-2,1,-3,4,-1,2,1,-5,4}));\n" +
                          "    }\n" +
                          "}")
                  .build()
  };
}
