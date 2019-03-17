void createToken(char *name, int num, char *symbol, int precision) {
    initToken(name, num, symbol, precision);
    setBalance(num);
}

int transfer(char *to, int value) {
    if (getMyBalance() >= value) {
        setBalance(getMyBalance() - value);
        setOtherBalance(to, getOtherBalance(to) + value);
        return 1;
    }
    return 0;
}

int transferfrom(char* from, char* to, int value) {
    int allowance = allowed(from, getAdress());
    if (getOtherBalance(from) >= value && allowance >= value) {
        setOtherBalance(to, getOtherBalance(to) + value);
        setOtherBalance(from, getOtherBalance(from) - value);
        if (allowance < 10000) {
            setAllowed(from, allowed(from, getAdress()) - value);
            return 1;
        }
    }
}

int balanceOf(char* owner) {
    return getOtherBalance(owner);
}

int approve(char* spender, int value) {
    setAllowed(spender, value);
    return 1;
}

int allowance(char* owner, char* spender) {
    return allowed(owner, spender);
}
