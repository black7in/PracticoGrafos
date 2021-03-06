/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ed2202102;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;


public class ArbolMViasBusqueda<K extends Comparable<K>, V>
        implements IArbolBusqueda<K, V> {

    protected NodoMVias<K, V> raiz;
    protected int orden;
    protected static final int POSICION_INVALIDA = -1;
    protected static final int ORDEN_MINIMO = 3;

    public ArbolMViasBusqueda() {
        this.orden = ORDEN_MINIMO;
    }

    public ArbolMViasBusqueda(int orden) {
        if (orden < ArbolMViasBusqueda.ORDEN_MINIMO) {
            throw new RuntimeException("Orden invalido");
        }
        this.orden = orden;
    }

    @Override
    public void insertar(K claveAInsertar, V valorAInsertar) {
        if (valorAInsertar == NodoMVias.datoVacio()) {
            throw new NullPointerException("No se permite insertar valores nulos");
        }
        if (claveAInsertar == NodoMVias.datoVacio()) {
            throw new NullPointerException("No se permite insertar claves nulas");
        }
        if (this.esArbolVacio()) {
            this.raiz = new NodoMVias<>(this.orden, claveAInsertar, valorAInsertar);
            return;
        }
        NodoMVias<K, V> nodoActual = this.raiz;
        while (!NodoMVias.esNodoVacio(nodoActual)) {
            int posicionDeClaveAInsertar = this.obtenerPosicionDeClave(nodoActual, claveAInsertar);
            if (posicionDeClaveAInsertar != POSICION_INVALIDA) { //SI LA CLAVE ESTA EN EL NODO
                nodoActual.setValor(posicionDeClaveAInsertar, valorAInsertar);
                return;
            } else {
                if (nodoActual.esHoja()) {
                    if (nodoActual.estanClavesLlenas()) {
                        int posicionPorDondeBajar = this.obtenerPosicionPorDondeBajar(nodoActual, claveAInsertar);
                        NodoMVias<K, V> nuevoHijo = new NodoMVias<>(this.orden, claveAInsertar, valorAInsertar);
                        nodoActual.setHijo(posicionPorDondeBajar, nuevoHijo);
                    } else {
                        this.insertarClaveYValorOrdenadaEnNodo(nodoActual, claveAInsertar, valorAInsertar);
                    }
                    return;
                } else {
                    int posicionPorDondeBajar = this.obtenerPosicionPorDondeBajar(nodoActual, claveAInsertar);
                    if (nodoActual.esHijoVacio(posicionPorDondeBajar)) {
                        NodoMVias<K, V> nuevoHijo = new NodoMVias<>(this.orden, claveAInsertar, valorAInsertar);
                        nodoActual.setHijo(posicionPorDondeBajar, nuevoHijo);
                        return;
                    } else {
                        nodoActual = nodoActual.getHijo(posicionPorDondeBajar);
                    }

                }
            }
        }

    }

    private int obtenerPosicionDeClave(NodoMVias<K, V> nodoActual, K claveABuscar) {
        for (int i = 0; i < nodoActual.cantidadDeClavesNoVacias(); i++) {
            K claveActual = nodoActual.getClave(i);
            if (claveABuscar.compareTo(claveActual) == 0) {
                return i;
            }
        }
        return ArbolMViasBusqueda.POSICION_INVALIDA;
    }

    private int obtenerPosicionPorDondeBajar(NodoMVias<K, V> nodoActual, K claveAInsertar) {
        int i = 0;
        while (i < nodoActual.cantidadDeClavesNoVacias()) {
            if (claveAInsertar.compareTo(nodoActual.getClave(i)) < 0) {
                return i;
            }
            i++;
        }
        return nodoActual.cantidadDeClavesNoVacias();
    }

    private void insertarClaveYValorOrdenadaEnNodo(NodoMVias<K, V> nodoActual, K claveAInsertar, V valorAInsertar) {
        int cant = nodoActual.cantidadDeClavesNoVacias();
        for (int i = 0; i < cant; i++) {
            K claveActual = nodoActual.getClave(i);
            if (claveAInsertar.compareTo(claveActual) < 0) {
                for (int k = cant; k > i; k--) {
                    K clave = nodoActual.getClave(k - 1);
                    V valor = nodoActual.getValor(k - 1);
                    nodoActual.setClave(k, clave);
                    nodoActual.setValor(k, valor);
                }
                nodoActual.setClave(i, claveAInsertar);
                nodoActual.setValor(i, valorAInsertar);
                return;
            }
        }
        nodoActual.setClave(cant, claveAInsertar);
        nodoActual.setValor(cant, valorAInsertar);
    }

    @Override
    public V eliminar(K claveAEliminar) {
        V valorAEliminar = this.buscar(claveAEliminar);
        if (valorAEliminar == null) {
            throw new RuntimeException("El valor no existe");
        }
        this.raiz = eliminar(this.raiz, claveAEliminar);
        return valorAEliminar;
    }

    private NodoMVias<K, V> eliminar(NodoMVias<K, V> nodoActual, K claveAEliminar) {
        for (int i = 0; i < nodoActual.cantidadDeClavesNoVacias(); i++) {
            K claveActual = nodoActual.getClave(i);
            if (claveAEliminar.compareTo(claveActual) == 0) {
                if (nodoActual.esHoja()) {
                    this.eliminarClaveYValorDelNodo(nodoActual, i);
                    if (nodoActual.cantidadDeClavesNoVacias() == 0) {
                        return NodoMVias.nodoVacio();
                    }
                    return nodoActual;
                }
                //No es hoja el nodo Actual
                K claveDeReemplazo;
                if (hayHijosMasAdelante(nodoActual, i)) {
                    claveDeReemplazo = buscarClaveSucesoraInOrden(nodoActual, claveAEliminar);
                } else {
                    claveDeReemplazo = buscarClavePredecesoraInOrden(nodoActual, claveAEliminar);
                }
                V valorDeReemplazo = buscar(claveDeReemplazo);
                nodoActual = eliminar(nodoActual, claveDeReemplazo);
                nodoActual.setClave(i, claveDeReemplazo);
                nodoActual.setValor(i, valorDeReemplazo);
                return nodoActual;
            }
            //no esta en la posicion i del nodoActual
            if (claveAEliminar.compareTo(claveActual) < 0) {
                NodoMVias<K, V> supuestoNuevoHijo = this.eliminar(nodoActual.getHijo(i), claveAEliminar);
                nodoActual.setHijo(i, supuestoNuevoHijo);
                return nodoActual;
            }
        }
        //si llego aqui sin retornar quiere decir que nunca baje por ningun lado ni lo encontre
        NodoMVias<K, V> supuestoNuevoHijo = this.eliminar(nodoActual.getHijo(nodoActual.cantidadDeClavesNoVacias()), claveAEliminar);
        nodoActual.setHijo(nodoActual.cantidadDeClavesNoVacias(), supuestoNuevoHijo);
        return nodoActual;
    }

    private void eliminarClaveYValorDelNodo(NodoMVias<K, V> nodoActual, int i) {
        int cant = nodoActual.cantidadDeClavesNoVacias();
        for (int k = i; k < cant - 1; k++) {
            K claveNuevo = nodoActual.getClave(k + 1);
            V valorNuevo = nodoActual.getValor(k + 1);
            nodoActual.setClave(k, claveNuevo);
            nodoActual.setValor(k, valorNuevo);
        }
        nodoActual.setClave(cant - 1, (K) NodoMVias.datoVacio());
        nodoActual.setValor(cant - 1, (V) NodoMVias.datoVacio());
    }

    private boolean hayHijosMasAdelante(NodoMVias<K, V> nodoActual, int i) {
        for (int j = i + 1; j < orden; j++) {
            if (!NodoMVias.esNodoVacio(nodoActual.getHijo(j))) {
                return true;
            }
        }
        return false;
    }

    private K buscarClavePredecesoraInOrden(NodoMVias<K, V> nodoActual, K claveAEliminar) {
        if (this.contiene(claveAEliminar)) {
            List<K> datos = new LinkedList<>();
            this.recorrridoEnInOrden(nodoActual, datos);
            int ind = indiceDe(datos, claveAEliminar);
            return datos.get(ind - 1);
        }
        return (K) NodoMVias.datoVacio();
    }

    private K buscarClaveSucesoraInOrden(NodoMVias<K, V> nodoActual, K claveAEliminar) {
        if (this.contiene(claveAEliminar)) {
            List<K> datos = new LinkedList<>();
            recorrridoEnInOrden(nodoActual, datos);
            int ind = indiceDe(datos, claveAEliminar);
            return datos.get(ind + 1);
        }
        return (K) NodoMVias.datoVacio();
    }

    @Override
    public V buscar(K claveABuscar) {
        if (claveABuscar == NodoMVias.datoVacio()) {
            return (V) NodoMVias.datoVacio();
        }
        NodoMVias<K, V> nodoActual = this.raiz;
        while (!NodoMVias.esNodoVacio(nodoActual)) {
            boolean huboCambioDeNodo = false;
            for (int i = 0; i < nodoActual.cantidadDeClavesNoVacias()
                    && !huboCambioDeNodo; i++) {
                K claveActual = nodoActual.getClave(i);
                if (claveABuscar.compareTo(claveActual) == 0) {
                    return nodoActual.getValor(i);
                }
                if (claveABuscar.compareTo(claveActual) < 0) {
                    nodoActual = nodoActual.getHijo(i);
                    huboCambioDeNodo = true;
                }
            }
            if (!huboCambioDeNodo) {
                nodoActual = nodoActual.getHijo(this.orden - 1);
            }
        }

        return (V) NodoMVias.datoVacio();

    }

    private int indiceDe(List<K> datos, K clave) {
        for (int i = 0; i < datos.size(); i++) {
            K claveActual = datos.get(i);
            if (clave.compareTo(claveActual) == 0) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public boolean contiene(K clave) {
        return this.buscar(clave) != NodoMVias.nodoVacio();
    }

    @Override
    public int size() {
        return size(this.raiz);
    }

    private int size(NodoMVias nodoActual) {
        if (NodoMVias.esNodoVacio(nodoActual)) {
            return 0;
        }
        int sizeAcumulado = 0;
        for (int i = 0; i < orden; i++) {
            int sizeDeHijo = size(nodoActual.getHijo(i));
            sizeAcumulado += sizeDeHijo;
        }
        return sizeAcumulado + 1;
    }

    @Override
    public int altura() {
        return altura(this.raiz);
    }

    public int altura(NodoMVias<K, V> nodoActual) {
        if (NodoMVias.esNodoVacio(nodoActual)) {
            return 0;
        }
        int alturaMayor = 0;
        for (int i = 0; i < this.orden; i++) {
            int alturaActual = altura(nodoActual.getHijo(i));
            if (alturaActual > alturaMayor) {
                alturaMayor = alturaActual;
            }
        }
        return alturaMayor + 1;
    }

    @Override
    public void vaciar() {
        this.raiz = NodoMVias.nodoVacio();
    }

    @Override
    public boolean esArbolVacio() {
        return this.raiz == NodoMVias.nodoVacio();
    }

    @Override
    public List<K> recorridoPorNiveles() {
        List<K> recorrido = new ArrayList<>();
        if (this.esArbolVacio()) {
            return recorrido;
        }

        Queue<NodoMVias<K, V>> colaDeNodos = new LinkedList<>();
        colaDeNodos.offer(this.raiz);

        while (!colaDeNodos.isEmpty()) {
            NodoMVias<K, V> nodoActual = colaDeNodos.poll();
            for (int i = 0; i < nodoActual.cantidadDeClavesNoVacias(); i++) {
                recorrido.add(nodoActual.getClave(i));
                if (!nodoActual.esHijoVacio(i)) {
                    colaDeNodos.offer(nodoActual.getHijo(i));
                }
            }
            if (!nodoActual.esHijoVacio(nodoActual.cantidadDeClavesNoVacias())) {
                colaDeNodos.offer(nodoActual.getHijo(nodoActual.cantidadDeClavesNoVacias()));
            }
        }
        return recorrido;
    }

    @Override
    public List<K> recorridoEnPreOrden() {
        List<K> recorrido = new LinkedList<>();
        recorridoEnPreOrden(this.raiz, recorrido);
        return recorrido;
    }

    private void recorridoEnPreOrden(NodoMVias<K, V> nodoActual, List<K> recorrido) {
        if (NodoMVias.esNodoVacio(nodoActual)) {
            return;
        }
        int cant = nodoActual.cantidadDeClavesNoVacias();
        for (int i = 0; i < cant; i++) {
            K claveActual = nodoActual.getClave(i);
            recorrido.add(claveActual);
            recorridoEnPreOrden(nodoActual.getHijo(i), recorrido);
        }
        this.recorridoEnPreOrden(nodoActual.getHijo(cant), recorrido);
    }

    @Override
    public List<K> recorridoEnInOrden() {
        List<K> recorrido = new LinkedList<>();
        recorrridoEnInOrden(this.raiz, recorrido);
        return recorrido;
    }

    private void recorrridoEnInOrden(NodoMVias<K, V> nodoActual, List<K> recorrido) {
        if (NodoMVias.esNodoVacio(nodoActual)) {
            return;
        }
        int cant = nodoActual.cantidadDeClavesNoVacias();
        for (int i = 0; i < cant; i++) {
            K claveActual = nodoActual.getClave(i);
            this.recorrridoEnInOrden(nodoActual.getHijo(i), recorrido);
            recorrido.add(claveActual);
        }
        this.recorrridoEnInOrden(nodoActual.getHijo(cant), recorrido);
    }

    @Override
    public List<K> recorridoEnPostOrden() {
        LinkedList<K> recorrido = new LinkedList<>();
        recorridoEnPostOrden(this.raiz, recorrido);
        return recorrido;
    }

    private void recorridoEnPostOrden(NodoMVias<K, V> nodoActual, LinkedList<K> recorrido) {
        if (NodoMVias.esNodoVacio(nodoActual)) {
            return;
        }
        int cant = nodoActual.cantidadDeClavesNoVacias();
        recorridoEnPostOrden(nodoActual.getHijo(0), recorrido);
        for (int i = 0; i < cant; i++) {
            recorridoEnPostOrden(nodoActual.getHijo(i + 1), recorrido);
            recorrido.add(nodoActual.getClave(i));
        }
    }

    /* PRACTICO */
 /*Implemente un m??todo que retorne verdadero si solo hay nodos completos en el nivel n de 
un ??rbol m vias. Falso en caso contrario*/
    public boolean todosNodosCompletosEnNivelN(int nivelN) {
        Queue<NodoMVias<K, V>> colaDeNodos = new LinkedList<>();
        colaDeNodos.offer(this.raiz);
        return todosNodosCompletosEnNivelN(colaDeNodos, 0, nivelN);
    }

    private boolean todosNodosCompletosEnNivelN(Queue<NodoMVias<K, V>> colaDeNodos, int nivelActual, int nivelN) {
        if (colaDeNodos.isEmpty() && nivelActual == nivelN) {
            return false;
        }
        //ya estoy en el nivel cuestionado
        if (nivelActual == nivelN) {
            boolean todosNodosCompletos = true;
            while (!colaDeNodos.isEmpty() && true) {
                NodoMVias<K, V> nodoActualDeLaCola = colaDeNodos.poll();
                todosNodosCompletos = todosNodosCompletos && esNodoCompleto(nodoActualDeLaCola);
            }
            return todosNodosCompletos;
        }

        //NO ESTOY EN EL NIVEL CUESTIONADO
        //metemos el siguiente nivel a la cola
        int cantidadNivelActual = colaDeNodos.size();
        for (int i = 0; i < cantidadNivelActual; i++) {
            NodoMVias<K, V> nodoActualDeLaCola = colaDeNodos.poll();
            meterHijosEnCola(nodoActualDeLaCola, colaDeNodos);
        }
        nivelActual = nivelActual + 1;
        return todosNodosCompletosEnNivelN(colaDeNodos, nivelActual, nivelN);

    }

    public boolean esNodoCompleto(NodoMVias<K, V> nodoCuestionado) {
        return nodoCuestionado.estanClavesLlenas() && nodoCuestionado.cantidadDeHijossNoVacios() == orden;
    }

    public void meterHijosEnCola(NodoMVias<K, V> nodoAMeterHijos, Queue<NodoMVias<K, V>> colaDeNodos) {
        for (int i = 0; i < nodoAMeterHijos.cantidadDeClavesNoVacias(); i++) {
            NodoMVias<K, V> HijoEnPosicion = nodoAMeterHijos.getHijo(i);
            colaDeNodos.offer(HijoEnPosicion);
        }
        colaDeNodos.offer(nodoAMeterHijos.getHijo(nodoAMeterHijos.cantidadDeClavesNoVacias()));
    }

    /*14. Para un ??rbol m v??as implementar un m??todo que reciba otro ??rbol de par??metro y que 
retorne verdadero si los arboles son similares. Falso en caso contrario.*/
    public boolean esSimilar(ArbolMViasBusqueda<K, V> arbol2) {
        boolean flag = true;
        flag = esSimilar(this.raiz, arbol2.raiz, flag);
        return flag;
    }

    public boolean esSimilar(NodoMVias<K, V> p, NodoMVias<K, V> p2, boolean flag) {
        if (NodoMVias.esNodoVacio(p) && NodoMVias.esNodoVacio(p2)) {
            return true;
        }
        if ((!NodoMVias.esNodoVacio(p) && NodoMVias.esNodoVacio(p2))
                || NodoMVias.esNodoVacio(p) && NodoMVias.esNodoVacio(p2)) {
            return false;
        }

        for (int i = 0; i < orden; i++) {
            flag = flag && esSimilar(p.getHijo(i), p2.getHijo(i), flag);
        }
        if (flag) {
            for (int i = 0; i < orden; i++) {
                if (!p.esHijoVacio(i) && !p2.esHijoVacio(i)) {
                    flag = flag && true;
                }
                if (p.esHijoVacio(i) && p2.esHijoVacio(i)) {
                    flag = flag && true;
                }
                if ((!p.esHijoVacio(i) && p2.esHijoVacio(i))
                        || (p.esHijoVacio(i) && !p2.esHijoVacio(i))) {
                    flag = flag && false;
                }
            }
        }
        return flag;
    }
}
