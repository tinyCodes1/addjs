
const version: string | undefined = Deno.env.get('VERSION');

if (!version) {
    throw new Error('VERSION environment variable not found.');
}

let readmeContent: string = Deno.readTextFileSync('README.md');

readmeContent = readmeContent.replace(/:addjs:v.\../, `:addjs:${version}`);

Deno.writeTextFileSync('README.md', readmeContent);

