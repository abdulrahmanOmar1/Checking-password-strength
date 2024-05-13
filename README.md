
<h1>SecurityAssignmentWithHint</h1>

<p>This JavaFX application helps users create strong passwords and provides feedback on password strength.</p>

<h2>Features</h2>

<ul>
    <li>Checks password length (minimum 8 characters).</li>
    <li>Identifies common words in passwords (uses a separate file named "commonWord").</li>
    <li>Analyzes password strength based on a point system considering:</li>
        <ul>
            <li>Presence of uppercase letters</li>
            <li>Presence of lowercase letters</li>
            <li>Presence of numbers</li>
            <li>Presence of special symbols</li>
        </ul>
    <li>Calculates password strength metrics:</li>
        <ul>
            <li>Variance</li>
            <li>Entropy</li>
        </ul>
    <li>Provides visual feedback on password strength using a colored rectangle (green for strong, orange for medium, red for weak, blue for common words).</li>
    <li>Offers a separate window displaying password strength creation rules with examples.</li>
</ul>

<h2>How to Use</h2>

1. Compile and run the code.
2. Enter your password in the text field.
3. Click the "Verify Password" button.
4. The application will display feedback on password strength and any suggestions for improvement.
5. Click the "See Rules to write Stringth Password" button to view password strength creation rules.

<h2>File Description</h2>

<ul>
    <li><strong>SecurityAssignmentWithHint.java</strong>: Main application code.</li>
    <li><strong>commonWord (text file)</strong>: This file contains a list of common words to be avoided in passwords (not provided in this example code).</li>
</ul>

<h2>Getting Started</h2>

1. Clone or download this repository.
2. Ensure you have JavaFX SDK installed (https://www.oracle.com/java/technologies/javase/javafx-overview.html).
3. Open the project in your preferred IDE (e.g., Eclipse, IntelliJ IDEA).
4. Compile and run the SecurityAssignmentWithHint.java class.

