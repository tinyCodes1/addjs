const ref : string | undefined = Deno.env.get('GITHUB_REF');

if (ref) {
 const version = ref.replace("/refs/tags/", "")
console.log(`output is: ${version} ... type: ${typeof version}`);
console.log(`version: ` + version.replace("/refs/tags/", ""))

console.log(`current directory is : ${Deno.cwd()}`);


let readmeContent: string = Deno.readTextFileSync('readme.md');

readmeContent = readmeContent.replace(/:addjs:v\[\d\]+\.\[\d\]+/, `:addjs:${version}`) ;
Deno.writeTextFileSync('readme.md', readmeContent);
}
