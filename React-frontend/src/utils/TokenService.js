
export const getUserFromToken = (token) => {
    if (!token) {
      return null;
    }

    const encodedPayload = token.split(".")[1];
    const decodedPayload = JSON.parse(atob(encodedPayload));

    // details[0] = username  && details[1] = name
    const details = decodedPayload.sub.split("-");
    return details;
  };