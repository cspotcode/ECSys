====================================================
ADDING CUSTOM HEADER TEMPLATE TO NETBEANS TEMPLATES:
====================================================

Netbeans will automatically add the GPL license header to any new files you
create.  In order for this to work you'll need to add the custom license header
to your Netbeans templates.  Otherwise new files will contain an error message
like this:

  Error reading included file Templates/Classes/../Licenses/license-faucetgpl30.txt

INSTRUCTIONS:
1. In Netbeans, go to Tools -> Templates.  The Template Manager appears.
2. Right-click the Licenses folder, and click Add...
3. You are prompted for a template file to add. Choose
   templates/license-faucetgpl30.txt.
4. The new template should appear in the Licenses folder.