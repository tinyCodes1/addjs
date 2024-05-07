const ref : string | undefined = Deno.env.get('GITHUB_REF');


if (ref) {
    const version = ref.replace("refs/tags/", "");
    console.log(`version is: ${version} ... type: ${typeof version}`);

    let readmeContent: string = Deno.readTextFileSync('readme.md');
console.log(readmeContent);
    readmeContent = readmeContent.replace(/simple/g, `complex`) ;
//    readmeContent = readmeContent.replace(/:addjs:v.*\..*\"/g, `:addjs:${version}`) ;
    Deno.writeTextFileSync('readme.md', readmeContent);
}
