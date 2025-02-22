import {graphConfig, loginRequest} from "./authConfig";
import {msalInstance} from "./index";

export async function callMsGraph() {
    const account = msalInstance.getActiveAccount();
    if (!account) {
        throw Error("No active account! Verify a user has been signed in and setActiveAccount has been called.");
    }

    try {
        const response = await msalInstance.acquireTokenSilent({
            ...loginRequest,
            account: account
        });

        const headers = new Headers();
        const bearer = `Bearer ${response.accessToken}`;

        headers.append("Authorization", bearer);

        const options = {
            method: "GET",
            headers: headers
        };

        return fetch(graphConfig.graphMeEndpoint, options)
            .then(response => response.json())
            .catch(error => console.log(error));
    }
    catch (e) {
        console.error("Error calling MS Graph API:", error);
        throw e;
    }
}
